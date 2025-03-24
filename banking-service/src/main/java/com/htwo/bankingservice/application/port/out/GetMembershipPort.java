package com.htwo.bankingservice.application.port.out;

public interface GetMembershipPort {
  MembershipStatus getMembership(String membershipId);
}
