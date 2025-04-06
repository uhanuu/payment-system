package com.htwo.moneyservice.application.port.out;

import com.htwo.moneyservice.domain.MembershipStatus;

public interface GetMembershipPort {
  MembershipStatus getMembership(String membershipId);
}
