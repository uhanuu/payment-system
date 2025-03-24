package com.htwo.bankingservice.application.port.out;

public record MembershipStatus(
    String membershipId,
    boolean isValid
) {
}
