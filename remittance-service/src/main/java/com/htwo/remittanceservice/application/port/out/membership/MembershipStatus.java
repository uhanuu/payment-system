package com.htwo.remittanceservice.application.port.out.membership;

public record MembershipStatus(
    String membershipId,
    boolean isValid
) {
}
