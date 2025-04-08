package com.htwo.moneyservice.application.service;

import com.htwo.common.UseCase;
import com.htwo.moneyservice.adapter.axon.command.CreateMemberMoneyCommand;
import com.htwo.moneyservice.application.port.in.CreateMemberMoneyRequestCommand;
import com.htwo.moneyservice.application.port.in.CreateMemberMoneyRequestUseCase;
import com.htwo.moneyservice.application.port.out.CreateMemberMoneyPort;
import com.htwo.moneyservice.domain.MemberMoney.MembershipId;
import com.htwo.moneyservice.domain.MemberMoney.MoneyAggregateIdentifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class CreateMoneyRequestService implements CreateMemberMoneyRequestUseCase {

  private final CommandGateway commandGateway;
  private final CreateMemberMoneyPort createMemberMoney;

  @Override
  public void createMoney(CreateMemberMoneyRequestCommand command) {
    CreateMemberMoneyCommand axonCommand = new CreateMemberMoneyCommand(command.getTargetMembershipId());
    commandGateway.send(axonCommand).whenComplete((result, throwable) -> {
      if (throwable != null) {
        log.error("throwable={}", throwable.getMessage());
        throw new RuntimeException(throwable);
      }
      createMemberMoney.createMemberMoney(
          new MembershipId(command.getTargetMembershipId()),
          new MoneyAggregateIdentifier(result.toString())
      );
    });
  }
}
