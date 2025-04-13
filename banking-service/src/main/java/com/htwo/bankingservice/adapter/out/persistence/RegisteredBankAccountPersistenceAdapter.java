package com.htwo.bankingservice.adapter.out.persistence;

import com.htwo.bankingservice.application.port.in.GetRegisteredBankAccountCommand;
import com.htwo.bankingservice.application.port.out.GetRegisteredBankAccountPort;
import com.htwo.bankingservice.application.port.out.RegisterBankAccountPort;
import com.htwo.bankingservice.domain.BankType;
import com.htwo.bankingservice.domain.FirmbankingRequest.AggregateIdentifier;
import com.htwo.bankingservice.domain.RegisteredBankAccount;
import com.htwo.bankingservice.domain.RegisteredBankAccount.BankAccountNumber;
import com.htwo.bankingservice.domain.RegisteredBankAccount.LinkedStatusIsValid;
import com.htwo.bankingservice.domain.RegisteredBankAccount.MembershipId;
import com.htwo.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisteredBankAccountPersistenceAdapter implements RegisterBankAccountPort, GetRegisteredBankAccountPort {

  private final RegisteredBankAccountJpaRepository registeredBankAccountJpaRepository;
  private final RegisteredBankAccountMapper mapper;

  @Override
  public RegisteredBankAccount createRegisteredBankAccount(
      MembershipId membershipId,
      BankAccountNumber bankAccountNumber,
      BankType bankType,
      LinkedStatusIsValid linkedStatusIsValid,
      AggregateIdentifier aggregateIdentifier
  ) {
    final RegisteredBankAccountJpaEntity registeredBankAccountJpaEntity = RegisteredBankAccountJpaEntity.builder()
        .membershipId(membershipId.getMembershipId())
        .bankAccountNumber(bankAccountNumber.getBankAccountNumber())
        .bankType(bankType)
        .linkedStatusIsValid(linkedStatusIsValid.isLinkedStatusIsValid())
        .aggregateIdentifier(aggregateIdentifier.aggregateIdentifier())
        .build();

    registeredBankAccountJpaRepository.save(registeredBankAccountJpaEntity);
    return mapper.mapToDomainEntity(registeredBankAccountJpaEntity);
  }

  @Override
  public RegisteredBankAccount getRegisteredBankAccount(GetRegisteredBankAccountCommand command) {
    final RegisteredBankAccountJpaEntity entity = registeredBankAccountJpaRepository.findByMembershipId(
            command.getMembershipId()
        )
        .orElseThrow(RuntimeException::new);

    return mapper.mapToDomainEntity(entity);
  }
}
