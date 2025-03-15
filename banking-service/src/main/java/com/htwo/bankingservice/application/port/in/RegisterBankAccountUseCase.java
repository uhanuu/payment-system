package com.htwo.bankingservice.application.port.in;

import com.htwo.bankingservice.domain.RegisteredBankAccount;

public interface RegisterBankAccountUseCase {
  RegisteredBankAccount registerMembership(RegisterBankAccountCommand command);
}
