package com.htwo.membershipservice.application.service;


import com.htwo.common.UseCase;
import com.htwo.membershipservice.adapter.out.persistence.MembershipJpaEntity;
import com.htwo.membershipservice.adapter.out.persistence.MembershipMapper;
import com.htwo.membershipservice.application.port.in.FindMembershipCommand;
import com.htwo.membershipservice.application.port.in.FindMembershipUseCase;
import com.htwo.membershipservice.application.port.out.FindMembershipPort;
import com.htwo.membershipservice.domain.Membership;
import com.htwo.membershipservice.domain.Membership.MembershipId;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindMembershipService implements FindMembershipUseCase {

  private final FindMembershipPort findMembershipPort;
  private final MembershipMapper membershipMapper;

  @Override
  public Membership findMembership(FindMembershipCommand command) {
    MembershipJpaEntity membershipJpaEntity = findMembershipPort.searchMembership(new MembershipId(command.getMembershipId()));
    return membershipMapper.mapToDomainEntity(membershipJpaEntity);
  }

}
