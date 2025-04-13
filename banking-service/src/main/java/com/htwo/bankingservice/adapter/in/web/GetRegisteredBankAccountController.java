package com.htwo.bankingservice.adapter.in.web;

import com.htwo.bankingservice.application.port.in.GetRegisteredBankAccountCommand;
import com.htwo.bankingservice.application.port.in.GetRegisteredBankAccountUseCase;
import com.htwo.bankingservice.domain.RegisteredBankAccount;
import com.htwo.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@WebAdapter(path = "/api/v1/banking/account")
@RequiredArgsConstructor
public class GetRegisteredBankAccountController {

  private final GetRegisteredBankAccountUseCase getRegisteredBankAccountUseCase;

  @GetMapping(path = "/{membershipId}")
  RegisteredBankAccount getRegisteredBankAccount(@PathVariable String membershipId) {
    GetRegisteredBankAccountCommand command = GetRegisteredBankAccountCommand.builder()
        .membershipId(membershipId).build();

    return getRegisteredBankAccountUseCase.getRegisteredBankAccount(command);
  }
}
