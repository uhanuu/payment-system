package com.htwo.remittanceservice.adapter.out.web.service.membership;

public record Membership(
    String membershipId,
    String name,
    String email,
    String address,
    boolean valid,
    boolean corp
) {
}
