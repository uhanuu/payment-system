package com.htwo.bankingservice.adapter.out.external.bank;

import com.htwo.bankingservice.domain.BankType;

public record BankAccount(
    String bankName,
    String bankAccountNumber,
    boolean isValid
) {
  public BankAccount {

  }
}
