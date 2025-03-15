package com.htwo.bankingservice.application.port.out;

import com.htwo.bankingservice.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.htwo.bankingservice.domain.RegisteredBankAccount.MembershipId;
import com.htwo.bankingservice.domain.RegisteredBankAccount.RegisteredBankAccountId;

public interface FindRegisteredBankAccountPort {
  RegisteredBankAccountJpaEntity searchRegisteredBankAccount(
      RegisteredBankAccountId registeredBankAccountId,
      MembershipId membershipId
  );
}
