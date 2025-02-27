package com.htwo.membershipservice.adapter.out.persistence;

import com.htwo.membershipservice.domain.Membership;
import com.htwo.membershipservice.domain.Membership.MembershipAddress;
import com.htwo.membershipservice.domain.Membership.MembershipEmail;
import com.htwo.membershipservice.domain.Membership.MembershipId;
import com.htwo.membershipservice.domain.Membership.MembershipIsCorp;
import com.htwo.membershipservice.domain.Membership.MembershipIsValid;
import com.htwo.membershipservice.domain.Membership.MembershipName;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapper {
  public Membership mapToDomainEntity(MembershipJpaEntity membershipJpaEntity) {
    return Membership.generateMember(
        new MembershipId(String.valueOf(membershipJpaEntity.getMembershipId())),
        new MembershipName(membershipJpaEntity.getName()),
        new MembershipEmail(membershipJpaEntity.getEmail()),
        new MembershipAddress(membershipJpaEntity.getAddress()),
        new MembershipIsValid(membershipJpaEntity.isValid()),
        new MembershipIsCorp(membershipJpaEntity.isCorp())
    );
  }

}
