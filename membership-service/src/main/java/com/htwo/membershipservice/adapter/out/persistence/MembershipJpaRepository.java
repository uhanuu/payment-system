package com.htwo.membershipservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipJpaRepository extends JpaRepository<MembershipJpaEntity, Long> {

}
