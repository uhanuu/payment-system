package com.htwo.bankingservice.adapter.in.web;

import com.htwo.bankingservice.application.port.in.RegisterBankAccountCommand;
import com.htwo.bankingservice.application.port.in.RegisterBankAccountUseCase;
import com.htwo.bankingservice.domain.BankType;
import com.htwo.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@WebAdapter(path = "/api/v1")
@RequiredArgsConstructor
public class RegisterBankAccountController {

  private final RegisterBankAccountUseCase registerBankAccountUseCase;

  @PostMapping(path = "/banking/account")
  public void registerBankAccount(@RequestBody RegisterBankAccountRequest request) {
    final BankType bankType = BankType.getBankType(request.bankName());
    final RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
        .membershipId(request.membershipId())
        .bankAccountNumber(request.bankAccountNumber())
        .bankType(bankType)
        .linkedStatusIsValid(request.linkedStatusIsValid())
        .build();

    registerBankAccountUseCase.registerMembership(command);
  }

}
