package com.htwo.bankingservice.adapter.in.web;

import com.htwo.bankingservice.application.port.in.RequestFirmbankingCommand;
import com.htwo.bankingservice.application.port.in.RequestFirmbankingUseCase;
import com.htwo.bankingservice.domain.BankType;
import com.htwo.bankingservice.domain.FirmbankingRequest;
import com.htwo.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@WebAdapter(path = "/api/v1")
@RequiredArgsConstructor
public class RequestFirmbankingController {

  private final RequestFirmbankingUseCase requestFirmBankingUseCase;

  @PostMapping(path = "/banking/firm-banking")
  public ResponseEntity<FirmbankingRequest> requestFirmBanking(
      @RequestBody RequestFirmbankingRequest request
  ) {
    final BankType fromBankType = BankType.getBankType(request.fromBankName());
    final BankType toBankType = BankType.getBankType(request.toBankName());

    final RequestFirmbankingCommand command = RequestFirmbankingCommand.builder()
        .fromBankType(fromBankType)
        .fromBankAccountNumber(request.fromBankAccountNumber())
        .toBankType(toBankType)
        .toBankAccountNumber(request.toBankAccountNumber())
        .moneyAmount(request.moneyAmount())
        .build();

    return ResponseEntity.ok(requestFirmBankingUseCase.requestFirmBanking(command));
  }
}
