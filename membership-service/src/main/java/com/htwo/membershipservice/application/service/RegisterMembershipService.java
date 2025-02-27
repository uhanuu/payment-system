package com.htwo.membershipservice.application.service;


import com.htwo.common.UseCase;
import com.htwo.membershipservice.adapter.out.persistence.MembershipJpaEntity;
import com.htwo.membershipservice.adapter.out.persistence.MembershipMapper;
import com.htwo.membershipservice.application.port.in.RegisterMembershipCommand;
import com.htwo.membershipservice.application.port.in.RegisterMembershipUseCase;
import com.htwo.membershipservice.application.port.out.RegisterMembershipPort;
import com.htwo.membershipservice.domain.Membership;
import com.htwo.membershipservice.domain.Membership.MembershipAddress;
import com.htwo.membershipservice.domain.Membership.MembershipEmail;
import com.htwo.membershipservice.domain.Membership.MembershipIsCorp;
import com.htwo.membershipservice.domain.Membership.MembershipIsValid;
import com.htwo.membershipservice.domain.Membership.MembershipName;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterMembershipService implements RegisterMembershipUseCase {

  private final RegisterMembershipPort registerMembershipPort;
  private final MembershipMapper membershipMapper;

  @Override
  public Membership registerMembership(RegisterMembershipCommand command) {
    final MembershipJpaEntity membershipJpaEntity = registerMembershipPort.createMembership(
        new MembershipName(command.getName()),
        new MembershipEmail(command.getEmail()),
        new MembershipAddress(command.getAddress()),
        new MembershipIsValid(command.isValid()),
        new MembershipIsCorp(command.isCorp())
    );

    return membershipMapper.mapToDomainEntity(membershipJpaEntity);
  }
}
