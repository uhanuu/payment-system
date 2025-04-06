package com.htwo.remittanceservice.application.port.out.banking;

public record RemittanceBanking(
    String bankName,
    String bankAccountNumber,
    boolean isValid
) {
}
