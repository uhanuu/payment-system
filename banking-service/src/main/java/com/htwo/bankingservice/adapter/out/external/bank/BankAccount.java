package com.htwo.bankingservice.adapter.out.external.bank;

import com.htwo.bankingservice.domain.BankType;

public record BankAccount(
    BankType bankType,
    String bankAccountNumber,
    boolean isValid
) {
  public BankAccount {

  }
}
