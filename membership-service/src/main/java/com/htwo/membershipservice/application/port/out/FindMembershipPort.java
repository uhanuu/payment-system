package com.htwo.membershipservice.application.port.out;

import com.htwo.membershipservice.adapter.out.persistence.MembershipJpaEntity;
import com.htwo.membershipservice.domain.Membership.MembershipId;

public interface FindMembershipPort {
  MembershipJpaEntity searchMembership(MembershipId membershipId);
}
