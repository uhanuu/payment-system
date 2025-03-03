package com.htwo.membershipservice.application.service;


import com.htwo.common.UseCase;
import com.htwo.membershipservice.adapter.out.persistence.MembershipJpaEntity;
import com.htwo.membershipservice.adapter.out.persistence.MembershipMapper;
import com.htwo.membershipservice.application.port.in.ModifyMembershipCommand;
import com.htwo.membershipservice.application.port.in.ModifyMembershipUseCase;
import com.htwo.membershipservice.application.port.out.ModifyMembershipPort;
import com.htwo.membershipservice.domain.Membership;
import com.htwo.membershipservice.domain.Membership.MembershipAddress;
import com.htwo.membershipservice.domain.Membership.MembershipEmail;
import com.htwo.membershipservice.domain.Membership.MembershipId;
import com.htwo.membershipservice.domain.Membership.MembershipIsCorp;
import com.htwo.membershipservice.domain.Membership.MembershipIsValid;
import com.htwo.membershipservice.domain.Membership.MembershipName;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class ModifyMembershipService implements ModifyMembershipUseCase {

  private final ModifyMembershipPort modifyMembershipPort;
  private final MembershipMapper membershipMapper;

  @Override
  public Membership modifyMembership(ModifyMembershipCommand command) {
    final MembershipJpaEntity membershipJpaEntity = modifyMembershipPort.modifyMembership(
        new MembershipId(command.getMembershipId()),
        new MembershipName(command.getName()),
        new MembershipEmail(command.getEmail()),
        new MembershipAddress(command.getAddress()),
        new MembershipIsValid(command.getIsValid()),
        new MembershipIsCorp(command.getIsCorp())
    );

    return membershipMapper.mapToDomainEntity(membershipJpaEntity);
  }
}
