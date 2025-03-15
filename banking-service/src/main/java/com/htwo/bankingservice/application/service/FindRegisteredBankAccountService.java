package com.htwo.bankingservice.application.service;

import com.htwo.bankingservice.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.htwo.bankingservice.adapter.out.persistence.RegisteredBankAccountMapper;
import com.htwo.bankingservice.application.port.in.FindRegisteredBankAccountQuery;
import com.htwo.bankingservice.application.port.in.FindRegisteredBankAccountUseCase;
import com.htwo.bankingservice.application.port.out.FindRegisteredBankAccountPort;
import com.htwo.bankingservice.domain.RegisteredBankAccount;
import com.htwo.bankingservice.domain.RegisteredBankAccount.MembershipId;
import com.htwo.bankingservice.domain.RegisteredBankAccount.RegisteredBankAccountId;
import com.htwo.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindRegisteredBankAccountService implements FindRegisteredBankAccountUseCase {

  private final FindRegisteredBankAccountPort findRegisteredBankAccountPort;
  private final RegisteredBankAccountMapper mapper;

  @Override
  public RegisteredBankAccount findRegisteredBankAccount(FindRegisteredBankAccountQuery query) {
    final RegisteredBankAccountJpaEntity registeredBankAccountJpaEntity = findRegisteredBankAccountPort.searchRegisteredBankAccount(
        new RegisteredBankAccountId(query.getRegisteredBankAccountId()),
        new MembershipId(query.getMembershipId())
    );

    return mapper.mapToDomainEntity(registeredBankAccountJpaEntity);
  }
}
