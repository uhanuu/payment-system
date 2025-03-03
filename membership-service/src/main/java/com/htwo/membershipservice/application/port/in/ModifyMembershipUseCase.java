package com.htwo.membershipservice.application.port.in;

import com.htwo.membershipservice.domain.Membership;

public interface ModifyMembershipUseCase {
  Membership modifyMembership(ModifyMembershipCommand command);
}
