package com.htwo.bankingservice.adapter.out.persistence;

import com.htwo.bankingservice.domain.RegisteredBankAccount.AggregateIdentifier;
import com.htwo.bankingservice.domain.RegisteredBankAccount;
import com.htwo.bankingservice.domain.RegisteredBankAccount.BankAccountNumber;
import com.htwo.bankingservice.domain.RegisteredBankAccount.BankName;
import com.htwo.bankingservice.domain.RegisteredBankAccount.LinkedStatusIsValid;
import com.htwo.bankingservice.domain.RegisteredBankAccount.MembershipId;
import com.htwo.bankingservice.domain.RegisteredBankAccount.RegisteredBankAccountId;
import org.springframework.stereotype.Component;

@Component
public class RegisteredBankAccountMapper {
  public RegisteredBankAccount mapToDomainEntity(RegisteredBankAccountJpaEntity registeredBankAccountJpaEntity) {
    return RegisteredBankAccount.generateBankAccount(
        new RegisteredBankAccountId(String.valueOf(registeredBankAccountJpaEntity.getRegisteredBankAccountId())),
        new MembershipId(registeredBankAccountJpaEntity.getMembershipId()),
        new BankAccountNumber(registeredBankAccountJpaEntity.getBankAccountNumber()),
        new BankName(registeredBankAccountJpaEntity.getBankType().getName()),
        new LinkedStatusIsValid(registeredBankAccountJpaEntity.isLinkedStatusIsValid()),
        new AggregateIdentifier(registeredBankAccountJpaEntity.getAggregateIdentifier())
    );
  }
}
