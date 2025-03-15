package com.htwo.bankingservice.application.port.out;

import com.htwo.bankingservice.adapter.out.external.bank.BankAccount;
import com.htwo.bankingservice.adapter.out.external.bank.GetBankAccountRequest;
import java.util.Optional;

public interface RequestBankAccountInfoPort {
  Optional<BankAccount> getBankAccountInfo(GetBankAccountRequest request);
}
