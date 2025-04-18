package com.htwo.bankingservice.adapter.out.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestFirmbankingJpaRepository extends JpaRepository<FirmbankingRequestJpaEntity, Long> {
  Optional<FirmbankingRequestJpaEntity> findByAggregateIdentifier(String aggregateIdentifier);
}
