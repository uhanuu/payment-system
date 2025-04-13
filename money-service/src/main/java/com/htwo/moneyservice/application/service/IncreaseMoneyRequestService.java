package com.htwo.moneyservice.application.service;

import com.htwo.common.UseCase;
import com.htwo.moneyservice.adapter.axon.command.RechargingMoneyRequestCreateCommand;
import com.htwo.moneyservice.adapter.out.persistence.MoneyChangingRequestMapper;
import com.htwo.moneyservice.application.port.in.IncreaseMoneyRequestCommand;
import com.htwo.moneyservice.application.port.in.IncreaseMoneyRequestUseCase;
import com.htwo.moneyservice.application.port.out.GetMemberMoneyPort;
import com.htwo.moneyservice.application.port.out.GetMembershipPort;
import com.htwo.moneyservice.application.port.out.IncreaseMemberMoneyPort;
import com.htwo.moneyservice.application.port.out.MoneyChangingRequestPort;
import com.htwo.moneyservice.domain.ChangingMoneyStatus;
import com.htwo.moneyservice.domain.ChangingMoneyType;
import com.htwo.moneyservice.domain.MemberMoney;
import com.htwo.moneyservice.domain.MemberMoney.MembershipId;
import com.htwo.moneyservice.domain.MembershipStatus;
import com.htwo.moneyservice.domain.Money;
import com.htwo.moneyservice.domain.MoneyChangingRequest.ChangingMoneyAmount;
import com.htwo.moneyservice.domain.MoneyChangingRequest.DomainChangingMoneyStatus;
import com.htwo.moneyservice.domain.MoneyChangingRequest.DomainChangingMoneyType;
import com.htwo.moneyservice.domain.MoneyChangingRequest.MoneyChangingRequestUuid;
import com.htwo.moneyservice.domain.MoneyChangingRequest.TargetMembershipId;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;

@Slf4j
@UseCase
//@Transactional
@RequiredArgsConstructor
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase {

  private final GetMemberMoneyPort getMemberMoneyPort;
  private final GetMembershipPort getMembershipPort;
  private final MoneyChangingRequestPort moneyChangingRequestPort;
  private final IncreaseMemberMoneyPort increaseMemberMoneyPort;
  private final MoneyChangingRequestMapper mapper;
  private final CommandGateway commandGateway;

  // TODO: 외부 API 트랜잭션 분리
  @Override
  public void increaseMoneyRequest(IncreaseMoneyRequestCommand command) {
    MembershipStatus membership = getMembershipPort.getMembership(command.getTargetMembershipId());
    if (!membership.isValid()) {
      throw new IllegalArgumentException();
    }
    final MemberMoney memberMoney = getMemberMoneyPort.getMemberMoney(
        new MembershipId(command.getTargetMembershipId()));

    // Saga 시작
    commandGateway.send(
        new RechargingMoneyRequestCreateCommand(
            memberMoney.getMoneyAggregateIdentifier(),
            UUID.randomUUID().toString(),
            command.getTargetMembershipId(),
            Integer.parseInt(command.getMoneyAmount())
        )
    ).whenComplete(
        (result, throwable) -> {
          if (throwable != null) {
            throw new RuntimeException();
          }
        }
    );

    commandGateway.send(
        IncreaseMoneyRequestCommand.builder()
            .targetMembershipId(command.getTargetMembershipId())
            .moneyAmount(command.getMoneyAmount())
            .aggregateIdentifier(memberMoney.getMoneyAggregateIdentifier())
            .build()
    ).whenComplete(
        (result, throwable) -> {
          if (throwable != null) {
            log.error("throwable={}", throwable.getMessage());
            throw new RuntimeException(throwable);
          }
          final Money moneyAmount = new Money(command.getMoneyAmount());
          increaseMemberMoneyPort.increaseMemberMoney(
              new MembershipId(command.getTargetMembershipId()), moneyAmount
          );

          moneyChangingRequestPort.createMoneyChangingRequest(
              new TargetMembershipId(command.getTargetMembershipId()),
              new DomainChangingMoneyType(ChangingMoneyType.INCREASE),
              new ChangingMoneyAmount(moneyAmount),
              new DomainChangingMoneyStatus(ChangingMoneyStatus.REQUESTED),
              new MoneyChangingRequestUuid(UUID.randomUUID().toString())
          );
        }
    );
  }

}
