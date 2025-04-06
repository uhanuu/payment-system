package com.htwo.remittanceservice.adapter.in.web;

public record RequestRemittanceRequest(
    String fromMembershipId,
    String toMembershipId,
    String toBankName,
    String toBankAccountNumber,
    String remittanceType, // INTERNAL: membership(내부 고객), EXTERNAL: bank (외부 은행 계좌)
    String amount
) {
}
