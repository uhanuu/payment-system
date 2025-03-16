package com.htwo.bankingservice.adapter.out.external.bank;

public record GetBankAccountRequest(
    String bankName,
    String bankAccountNumber
) {
}
