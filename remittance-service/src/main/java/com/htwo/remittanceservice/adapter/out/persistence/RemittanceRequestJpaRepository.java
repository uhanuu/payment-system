package com.htwo.remittanceservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface RemittanceRequestJpaRepository extends JpaRepository<RemittanceRequestJpaEntity, Long> {
}
