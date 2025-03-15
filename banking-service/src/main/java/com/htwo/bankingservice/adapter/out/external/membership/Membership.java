package com.htwo.bankingservice.adapter.out.external.membership;

public record Membership(
    String membershipId,
    String name,
    String email,
    String address,
    boolean isValid,
    boolean isCorp
) {
}
