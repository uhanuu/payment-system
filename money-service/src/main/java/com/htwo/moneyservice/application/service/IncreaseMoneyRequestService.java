package com.htwo.moneyservice.application.service;

import com.htwo.common.UseCase;
import com.htwo.moneyservice.adapter.out.persistence.MemberMoneyJpaEntity;
import com.htwo.moneyservice.adapter.out.persistence.MoneyChangingRequestJpaEntity;
import com.htwo.moneyservice.adapter.out.persistence.MoneyChangingRequestMapper;
import com.htwo.moneyservice.application.port.in.IncreaseMoneyRequestCommand;
import com.htwo.moneyservice.application.port.in.IncreaseMoneyRequestUseCase;
import com.htwo.moneyservice.application.port.out.MemberMoneyPort;
import com.htwo.moneyservice.application.port.out.MoneyChangingRequestPort;
import com.htwo.moneyservice.domain.ChangingMoneyStatus;
import com.htwo.moneyservice.domain.ChangingMoneyType;
import com.htwo.moneyservice.domain.MemberMoney.MembershipId;
import com.htwo.moneyservice.domain.Money;
import com.htwo.moneyservice.domain.MoneyChangingRequest;
import com.htwo.moneyservice.domain.MoneyChangingRequest.ChangingMoneyAmount;
import com.htwo.moneyservice.domain.MoneyChangingRequest.DomainChangingMoneyStatus;
import com.htwo.moneyservice.domain.MoneyChangingRequest.DomainChangingMoneyType;
import com.htwo.moneyservice.domain.MoneyChangingRequest.MoneyChangingRequestUuid;
import com.htwo.moneyservice.domain.MoneyChangingRequest.TargetMembershipId;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UseCase
//@Transactional
@RequiredArgsConstructor
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase {

  private final MoneyChangingRequestPort moneyChangingRequestPort;
  private final MemberMoneyPort memberMoneyPort;
  private final MoneyChangingRequestMapper mapper;

  // TODO: 외부 API 트랜잭션 분리
  @Override
  public MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command) {
    final UUID moneyChangingRequestUuid = UUID.randomUUID();
    final Money moneyAmount = new Money(command.getMoneyAmount());

    log.info("[{}] increase member money", moneyChangingRequestUuid);
    memberMoneyPort.increaseMemberMoney(
        new MembershipId(command.getTargetMembershipId()), moneyAmount
    );

    log.info("[{}] create money changing request", moneyChangingRequestUuid);
    MoneyChangingRequestJpaEntity moneyChangingRequest = moneyChangingRequestPort.createMoneyChangingRequest(
        new TargetMembershipId(command.getTargetMembershipId()),
        new DomainChangingMoneyType(ChangingMoneyType.INCREASE),
        new ChangingMoneyAmount(moneyAmount),
        new DomainChangingMoneyStatus(ChangingMoneyStatus.REQUESTED),
        new MoneyChangingRequestUuid(moneyChangingRequestUuid.toString())
    );
    return mapper.mapToDomainEntity(moneyChangingRequest);
  }
}
