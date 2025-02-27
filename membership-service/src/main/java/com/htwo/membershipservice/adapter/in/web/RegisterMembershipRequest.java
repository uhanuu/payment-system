package com.htwo.membershipservice.adapter.in.web;

public record RegisterMembershipRequest(
    String name,
    String email,
    String address,
    boolean isCorp
) {
}
