package com.htwo.bankingservice.adapter.in.web;

public record UpdateFirmBankingRequest(
    String firmBankingAggregateIdentifier,
    String status
) {
}
