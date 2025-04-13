package com.htwo.bankingservice.application.port.out;

import com.htwo.bankingservice.application.port.in.GetRegisteredBankAccountCommand;
import com.htwo.bankingservice.domain.RegisteredBankAccount;

public interface GetRegisteredBankAccountPort {
  RegisteredBankAccount getRegisteredBankAccount(GetRegisteredBankAccountCommand command);
}
