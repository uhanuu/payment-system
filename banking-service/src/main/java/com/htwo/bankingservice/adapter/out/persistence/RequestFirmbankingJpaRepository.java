package com.htwo.bankingservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestFirmbankingJpaRepository extends JpaRepository<FirmbankingRequestJpaEntity, Long> {
}
