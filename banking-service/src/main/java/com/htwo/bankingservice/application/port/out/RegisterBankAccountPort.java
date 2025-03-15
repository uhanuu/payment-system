package com.htwo.bankingservice.application.port.out;

import com.htwo.bankingservice.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.htwo.bankingservice.domain.BankType;
import com.htwo.bankingservice.domain.RegisteredBankAccount.BankAccountNumber;
import com.htwo.bankingservice.domain.RegisteredBankAccount.LinkedStatusIsValid;
import com.htwo.bankingservice.domain.RegisteredBankAccount.MembershipId;

public interface RegisterBankAccountPort {
  RegisteredBankAccountJpaEntity createRegisteredBankAccount(
      MembershipId membershipId,
      BankAccountNumber bankAccountNumber,
      BankType bankType,
      LinkedStatusIsValid linkedStatusIsValid
  );

}
