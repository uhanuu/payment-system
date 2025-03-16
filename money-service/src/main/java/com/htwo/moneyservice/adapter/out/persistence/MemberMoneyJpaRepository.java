package com.htwo.moneyservice.adapter.out.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMoneyJpaRepository extends JpaRepository<MemberMoneyJpaEntity, Long> {
  Optional<MemberMoneyJpaEntity> findByMembershipId(String membershipId);
}
