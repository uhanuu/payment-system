package com.htwo.remittanceservice.application.port.out.money;

public record RemittanceMoney(
    String membershipId,
    String balance
) {
}
