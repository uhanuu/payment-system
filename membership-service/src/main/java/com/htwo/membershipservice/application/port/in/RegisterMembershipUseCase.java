package com.htwo.membershipservice.application.port.in;

import com.htwo.membershipservice.domain.Membership;

public interface RegisterMembershipUseCase {
  Membership registerMembership(RegisterMembershipCommand command);
}
