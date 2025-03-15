package com.htwo.bankingservice.application.port.in;

import com.htwo.bankingservice.domain.RegisteredBankAccount;

public interface FindRegisteredBankAccountUseCase {
  RegisteredBankAccount findRegisteredBankAccount(FindRegisteredBankAccountQuery query);
}
