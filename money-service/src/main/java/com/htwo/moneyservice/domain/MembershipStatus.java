package com.htwo.moneyservice.domain;

public record MembershipStatus(
    String membershipId,
    boolean isValid
) {
}
