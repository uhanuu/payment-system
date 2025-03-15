package com.htwo.bankingservice.adapter.out.external.bank;

import com.htwo.bankingservice.domain.BankType;

public record GetBankAccountRequest(
    BankType bankType,
    String bankAccountNumber
) {
}
