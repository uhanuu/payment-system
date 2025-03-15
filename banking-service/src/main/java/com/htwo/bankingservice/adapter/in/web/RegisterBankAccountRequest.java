package com.htwo.bankingservice.adapter.in.web;

public record RegisterBankAccountRequest(
    String membershipId,
    String bankAccountNumber,
    String bankName,
    boolean linkedStatusIsValid
) {
}
