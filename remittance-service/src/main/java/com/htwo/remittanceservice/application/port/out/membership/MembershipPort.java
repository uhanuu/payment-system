package com.htwo.remittanceservice.application.port.out.membership;

public interface MembershipPort {
  MembershipStatus getMembershipStatus(String membershipId);
}
