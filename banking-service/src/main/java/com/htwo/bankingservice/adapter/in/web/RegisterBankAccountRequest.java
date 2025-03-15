package com.htwo.bankingservice.adapter.in.web;

import com.htwo.bankingservice.domain.BankType;

public record RegisterBankAccountRequest(
    String membershipId,
    String bankAccountNumber,
    BankType bankType,
    boolean linkedStatusIsValid
) {
}
