package com.htwo.bankingservice.adapter.in.web;

public record RequestFirmbankingRequest(
    String fromBankName,
    String fromBankAccountNumber,
    String toBankName,
    String toBankAccountNumber,
    String moneyAmount // only won
) {
}
