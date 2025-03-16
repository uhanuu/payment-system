package com.htwo.moneyservice.adapter.in.web;

public record IncreaseMoneyChangingRequest(
    String targetMembershipId,
    String moneyAmount
) {
}
