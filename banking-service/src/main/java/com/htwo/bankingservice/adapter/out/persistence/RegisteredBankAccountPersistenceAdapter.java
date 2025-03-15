package com.htwo.bankingservice.adapter.out.persistence;

import com.htwo.bankingservice.application.port.out.RegisterBankAccountPort;
import com.htwo.bankingservice.domain.BankType;
import com.htwo.bankingservice.domain.RegisteredBankAccount.BankAccountNumber;
import com.htwo.bankingservice.domain.RegisteredBankAccount.LinkedStatusIsValid;
import com.htwo.bankingservice.domain.RegisteredBankAccount.MembershipId;
import com.htwo.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisteredBankAccountPersistenceAdapter implements RegisterBankAccountPort {

  private final RegisteredBankAccountJpaRepository registeredBankAccountJpaRepository;

  @Override
  public RegisteredBankAccountJpaEntity createRegisteredBankAccount(
      MembershipId membershipId,
      BankAccountNumber bankAccountNumber,
      BankType bankType,
      LinkedStatusIsValid linkedStatusIsValid
  ) {
    final RegisteredBankAccountJpaEntity registeredBankAccountJpaEntity = RegisteredBankAccountJpaEntity.builder()
        .membershipId(membershipId.getMembershipId())
        .bankAccountNumber(bankAccountNumber.getBankAccountNumber())
        .bankType(bankType)
        .linkedStatusIsValid(linkedStatusIsValid.isLinkedStatusIsValid())
        .build();

    return registeredBankAccountJpaRepository.save(registeredBankAccountJpaEntity);
  }
}
