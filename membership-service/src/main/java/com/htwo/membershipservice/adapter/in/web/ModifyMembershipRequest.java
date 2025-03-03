package com.htwo.membershipservice.adapter.in.web;

public record ModifyMembershipRequest(
    String name,
    String email,
    String address,
    Boolean isValid,
    Boolean isCorp
) {
}
