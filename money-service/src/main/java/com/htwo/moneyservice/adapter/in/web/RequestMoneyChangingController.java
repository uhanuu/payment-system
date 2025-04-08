package com.htwo.moneyservice.adapter.in.web;

import com.htwo.common.WebAdapter;
import com.htwo.moneyservice.application.port.in.CreateMemberMoneyRequestCommand;
import com.htwo.moneyservice.application.port.in.CreateMemberMoneyRequestUseCase;
import com.htwo.moneyservice.application.port.in.IncreaseMoneyRequestCommand;
import com.htwo.moneyservice.application.port.in.IncreaseMoneyRequestUseCase;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@WebAdapter(path = "/api/v1")
@RequiredArgsConstructor
public class RequestMoneyChangingController {

  private final IncreaseMoneyRequestUseCase increaseMoneyRequestUseCase;
  private final CreateMemberMoneyRequestUseCase createMemberMoneyRequestUseCase;

  @PostMapping(path = "/money/increase")
  public void increaseMoneyRequest(@RequestBody IncreaseMoneyChangingRequest request) {
    final IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand.builder()
        .targetMembershipId(request.targetMembershipId())
        .moneyAmount(request.moneyAmount())
        .aggregateIdentifier(UUID.randomUUID().toString())
        .build();

    increaseMoneyRequestUseCase.increaseMoneyRequest(command);
  }

  @PostMapping("/money")
  void createMemberMoney(@RequestBody CreateMemberMoneyRequest request) {
    CreateMemberMoneyRequestCommand command = CreateMemberMoneyRequestCommand.builder()
        .targetMembershipId(request.targetMembershipId())
        .build();

    createMemberMoneyRequestUseCase.createMoney(command);
  }
}