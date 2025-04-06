package com.htwo.remittanceservice.adapter.in.web;

import com.htwo.common.WebAdapter;
import com.htwo.remittanceservice.application.port.in.RequestRemittanceCommand;
import com.htwo.remittanceservice.application.port.in.RequestRemittanceUseCase;
import com.htwo.remittanceservice.domain.RemittanceRequest;
import com.htwo.remittanceservice.domain.RemittanceType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@WebAdapter("/api/v1")
@RequiredArgsConstructor
public class RequestRemittanceController {

  private final RequestRemittanceUseCase requestRemittanceUseCase;

  @PostMapping(path = "/remittance/request")
  ResponseEntity<RemittanceRequest> requestRemittance(@RequestBody RequestRemittanceRequest request) {
    RequestRemittanceCommand command = RequestRemittanceCommand.builder()
        .fromMembershipId(request.fromMembershipId())
        .toMembershipId(request.toMembershipId())
        .toBankName(request.toBankName())
        .toBankAccountNumber(request.toBankAccountNumber())
        .amount(request.amount())
        .remittanceType(RemittanceType.getInstance(request.remittanceType()))
        .build();

    return ResponseEntity.ok(requestRemittanceUseCase.requestRemittance(command));
  }
}
