package com.htwo.bankingservice.adapter.out.persistence;

import com.htwo.bankingservice.application.port.out.FindRegisteredBankAccountPort;
import com.htwo.bankingservice.domain.RegisteredBankAccount.MembershipId;
import com.htwo.bankingservice.domain.RegisteredBankAccount.RegisteredBankAccountId;
import com.htwo.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class FindRegisteredBankAccountAdapter implements FindRegisteredBankAccountPort {

  private final RegisteredBankAccountJpaRepository registeredBankAccountJpaRepository;

  @Override
  public RegisteredBankAccountJpaEntity searchRegisteredBankAccount(
      RegisteredBankAccountId registeredBankAccountId,
      MembershipId membershipId
  ) {
    final Long parseLongRegisteredBankAccountId = parseLongRegisteredBankAccountId(registeredBankAccountId);

    return registeredBankAccountJpaRepository.findByRegisteredBankAccountIdAndMembershipId(
            parseLongRegisteredBankAccountId, membershipId.getMembershipId())
        .orElseThrow(RuntimeException::new);
  }

  private Long parseLongRegisteredBankAccountId(RegisteredBankAccountId registeredBankAccountId) {
    try {
      return Long.parseLong(registeredBankAccountId.getRegisteredBankAccount());
    } catch (NumberFormatException e) {
      throw new RuntimeException();
    }
  }
}
