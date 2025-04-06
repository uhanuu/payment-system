package com.htwo.remittanceservice.application.service;

import com.htwo.common.UseCase;
import com.htwo.remittanceservice.application.port.in.RequestRemittanceCommand;
import com.htwo.remittanceservice.application.port.in.RequestRemittanceUseCase;
import com.htwo.remittanceservice.application.port.out.RequestRemittancePort;
import com.htwo.remittanceservice.application.port.out.banking.BankingPort;
import com.htwo.remittanceservice.application.port.out.membership.MembershipPort;
import com.htwo.remittanceservice.application.port.out.membership.MembershipStatus;
import com.htwo.remittanceservice.application.port.out.money.MoneyPort;
import com.htwo.remittanceservice.application.port.out.money.RemittanceMoney;
import com.htwo.remittanceservice.domain.Money;
import com.htwo.remittanceservice.domain.RemittanceRequest;
import com.htwo.remittanceservice.domain.RemittanceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class RequestRemittanceService implements RequestRemittanceUseCase {

  private final RequestRemittancePort requestRemittancePort;
  private final MembershipPort membershipPort;
  private final MoneyPort moneyPort;
  private final BankingPort bankingPort;


  @Override
  @Transactional
  public RemittanceRequest requestRemittance(RequestRemittanceCommand command) {
    log.info("0. 송금 요청 상태를 시작 상태로 기록");
    RemittanceRequest remittanceRequest = requestRemittancePort.createRemittanceRequestHistory(command);

    log.info("1. from 멤버십 상태 확인 (membership Svc)");
    MembershipStatus membershipStatus = membershipPort.getMembershipStatus(command.getFromMembershipId());
    if (!membershipStatus.isValid()) {
      log.error("1. from 멤버십 상태 확인 (membership Svc)");
      throw new IllegalStateException("1. from 멤버십 상태 확인 (membership Svc)");
    }

    log.info("2. 잔액 존재하는지 확인 (money svc)");
    RemittanceMoney remittanceMoney = moneyPort.getMoneyInfo(command.getFromMembershipId());
    final Money remittanceBalance = new Money(remittanceMoney.balance());
    final Money requestAmount = new Money(command.getAmount());

    if(remittanceBalance.isLessThan(requestAmount)) {
      log.info("잔액이 충분치 않아 충전 로직");
      // command.getAmount() - remittanceMoney.getBalance() 이후 만원 단위로 올림하는 연산
      // 2. 10000 단위로 올림하여 충전 금액 계산
      Money rechargeAmount = requestAmount.subtract(remittanceBalance)
          .roundUpToNearestTenThousand();
      log.info("requestAmount={} rechargeAmount={}", requestAmount.toStringValue(), rechargeAmount.toStringValue());

      // 2-1. 잔액이 충분하지 않다면, 충전 요청 (money svc)
      boolean moneyResult = moneyPort.requestMoneyRecharging(command.getFromMembershipId(), rechargeAmount.toStringValue());
      if (!moneyResult){
        log.error("2-1. 잔액이 충분하지 않다면, 충전 요청 (money svc)");
        throw new IllegalStateException("2-1. 잔액이 충분하지 않다면, 충전 요청 (money svc)");
      }
    }

    // 3. 송금 타입 (고객/은행)
    if (command.getRemittanceType().isInternalAccount()) {
      log.info("3-1. 내부 고객일 경우");
      // from 고객 머니 감액, to 고객 머니 증액 (money svc)
      boolean fromRemittanceResult = moneyPort.requestMoneyDecrease(command.getFromMembershipId(), command.getAmount());
      boolean toRemittanceResult = moneyPort.requestMoneyIncrease(command.getToMembershipId(), command.getAmount());
      if (!fromRemittanceResult || !toRemittanceResult) {
        log.error("3. 송금 타입 (고객/은행)");
        throw new IllegalStateException("3. 송금 타입 (고객/은행)");
      }

    } else if (command.getRemittanceType().isExternalAccount()) {
      log.info("3-2. 외부 은행 계좌");
      // 외부 은행 계좌가 적절한지 확인 (banking svc)
      // 법인계좌 -> 외부 은행 계좌 펌뱅킹 요청 (banking svc)
      boolean remittanceResult = bankingPort.requestFirmBanking(command.getToBankName(), command.getToBankAccountNumber(), command.getAmount());
      if (!remittanceResult) {
        log.error("3-2. 외부 은행 계좌");
        throw new IllegalStateException("3-2. 외부 은행 계좌");
      }
    }

    log.info("4. 송금 요청 상태를 성공으로 기록 (persistence layer)");
    remittanceRequest.updateStatus(RemittanceStatus.COMPLETED);
    requestRemittancePort.saveRemittanceRequestHistory(remittanceRequest);
    return remittanceRequest;
  }

}
