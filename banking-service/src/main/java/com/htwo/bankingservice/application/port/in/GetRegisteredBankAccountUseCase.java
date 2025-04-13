package com.htwo.bankingservice.application.port.in;

import com.htwo.bankingservice.domain.RegisteredBankAccount;

public interface GetRegisteredBankAccountUseCase {
  RegisteredBankAccount getRegisteredBankAccount(GetRegisteredBankAccountCommand command);
}
