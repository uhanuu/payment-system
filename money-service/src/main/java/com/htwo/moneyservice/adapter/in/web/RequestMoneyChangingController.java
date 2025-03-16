package com.htwo.moneyservice.adapter.in.web;

import com.htwo.common.WebAdapter;
import com.htwo.moneyservice.application.port.in.IncreaseMoneyRequestCommand;
import com.htwo.moneyservice.application.port.in.IncreaseMoneyRequestUseCase;
import com.htwo.moneyservice.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@WebAdapter(path = "/api/v1")
@RequiredArgsConstructor
public class RequestMoneyChangingController {

  private final IncreaseMoneyRequestUseCase increaseMoneyRequestUseCase;
//  private final DecreaseMoneyRequestUseCase decreaseMoneyRequestUseCase;

  @PostMapping(path = "/money/increase")
  public ResponseEntity<MoneyChangingResultDetails> increaseMoneyRequest(@RequestBody IncreaseMoneyChangingRequest request) {
    final IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand.builder()
        .targetMembershipId(request.targetMembershipId())
        .moneyAmount(request.moneyAmount())
        .build();

    MoneyChangingRequest moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoneyRequest(command);

    return ResponseEntity.ok(MoneyChangingResultDetails.from(moneyChangingRequest));
  }

}
