package com.htwo.bankingservice.adapter.out.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredBankAccountJpaRepository extends JpaRepository<RegisteredBankAccountJpaEntity, Long> {
  Optional<RegisteredBankAccountJpaEntity> findByRegisteredBankAccountIdAndMembershipId(
      Long registeredBankAccountId,
      String membershipId
  );
}
