package com.htwo.membershipservice.adapter.out.persistence;

import com.htwo.membershipservice.application.port.out.FindMembershipPort;
import com.htwo.membershipservice.application.port.out.RegisterMembershipPort;
import com.htwo.common.PersistenceAdapter;
import com.htwo.membershipservice.domain.Membership.MembershipAddress;
import com.htwo.membershipservice.domain.Membership.MembershipEmail;
import com.htwo.membershipservice.domain.Membership.MembershipId;
import com.htwo.membershipservice.domain.Membership.MembershipIsCorp;
import com.htwo.membershipservice.domain.Membership.MembershipIsValid;
import com.htwo.membershipservice.domain.Membership.MembershipName;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort, FindMembershipPort {

  private final MembershipJpaRepository membershipJpaRepository;

  @Override
  public MembershipJpaEntity createMembership(
      MembershipName membershipName,
      MembershipEmail membershipEmail,
      MembershipAddress membershipAddress,
      MembershipIsValid membershipIsValid,
      MembershipIsCorp membershipIsCorp
  ) {
    final MembershipJpaEntity membershipJpaEntity = MembershipJpaEntity.builder()
        .name(membershipName.getNameValue())
        .email(membershipEmail.getEmailValue())
        .address(membershipAddress.getAddressValue())
        .isValid(membershipIsValid.isValidValue())
        .isCorp(membershipIsCorp.isCorpValue())
        .build();

    return membershipJpaRepository.save(membershipJpaEntity);
  }

  @Override
  public MembershipJpaEntity searchMembership(MembershipId membershipId) {
    return membershipJpaRepository.findById(Long.parseLong(membershipId.getMembershipId()))
        .orElseThrow(RuntimeException::new);
  }
}
