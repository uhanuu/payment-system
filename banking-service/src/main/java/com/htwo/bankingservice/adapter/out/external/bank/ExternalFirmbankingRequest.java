package com.htwo.bankingservice.adapter.out.external.bank;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ExternalFirmbankingRequest {

  private final String fromBankName;
  private final String fromBankAccountNumber;
  private final String toBankName;
  private final String toBankAccountNumber;
  private final String moneyAmount;

  @Builder
  private ExternalFirmbankingRequest(
      String fromBankName,
      String fromBankAccountNumber,
      String toBankName,
      String toBankAccountNumber,
      String moneyAmount
  ) {
    this.fromBankName = fromBankName;
    this.fromBankAccountNumber = fromBankAccountNumber;
    this.toBankName = toBankName;
    this.toBankAccountNumber = toBankAccountNumber;
    this.moneyAmount = moneyAmount;
  }
}
