package com.htwo.bankingservice.adapter.out.web;

public record Membership(
    String membershipId,
    String name,
    String email,
    String address,
    // isValid => valid로 역직렬화
    boolean valid,
    // isCorp => corp로 역직렬화
    boolean corp
) {
}
