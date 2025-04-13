package com.htwo.bankingservice.application.service;

import com.htwo.bankingservice.application.port.in.GetRegisteredBankAccountCommand;
import com.htwo.bankingservice.application.port.in.GetRegisteredBankAccountUseCase;
import com.htwo.bankingservice.application.port.out.GetRegisteredBankAccountPort;
import com.htwo.bankingservice.domain.RegisteredBankAccount;
import com.htwo.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetBankAccountService implements GetRegisteredBankAccountUseCase {

  private final GetRegisteredBankAccountPort getRegisteredBankAccountPort;

  @Override
  public RegisteredBankAccount getRegisteredBankAccount(GetRegisteredBankAccountCommand command) {
    return getRegisteredBankAccountPort.getRegisteredBankAccount(command);
  }
}