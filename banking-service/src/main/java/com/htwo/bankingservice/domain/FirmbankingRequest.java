package com.htwo.bankingservice.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FirmbankingRequest {

  private final String firmBankingRequestId;
  private final BankType fromBankType;
  private final String fromBankAccountNumber;
  private final BankType toBankType;
  private final String toBankAccountNumber;
  private final Money moneyAmount;
  private final FirmbankingStatus firmBankingStatus;
  private final String firmbankingRequestUuid;

  @Builder(access = AccessLevel.PRIVATE)
  private FirmbankingRequest(
      String firmBankingRequestId,
      BankType fromBankType,
      String fromBankAccountNumber,
      BankType toBankType,
      String toBankAccountNumber,
      Money moneyAmount,
      FirmbankingStatus firmBankingStatus,
      String firmbankingRequestUuid
  ) {
    this.firmBankingRequestId = firmBankingRequestId;
    this.fromBankType = fromBankType;
    this.fromBankAccountNumber = fromBankAccountNumber;
    this.toBankType = toBankType;
    this.toBankAccountNumber = toBankAccountNumber;
    this.moneyAmount = moneyAmount;
    this.firmBankingStatus = firmBankingStatus;
    this.firmbankingRequestUuid = firmbankingRequestUuid;
  }


  public static FirmbankingRequest generateFirmbankingRequest(
      FirmBankingRequestId firmBankingRequestId,
      FromBankType fromBankType,
      FromBankAccountNumber fromBankAccountNumber,
      ToBankType toBankType,
      ToBankAccountNumber toBankAccountNumber,
      MoneyAmount moneyAmount,
      DomainFirmBankingStatus firmBankingStatus,
      FirmbankingRequestUuid firmbankingRequestUuid
  ) {
    return FirmbankingRequest.builder()
        .firmBankingRequestId(firmBankingRequestId.firmBankingRequestId)
        .fromBankType(fromBankType.fromBankType)
        .fromBankAccountNumber(fromBankAccountNumber.fromBankAccountNumber)
        .toBankType(toBankType.toBankType)
        .toBankAccountNumber(toBankAccountNumber.toBankAccountNumber)
        .moneyAmount(moneyAmount.moneyAmount)
        .firmBankingStatus(firmBankingStatus.firmBankingStatus)
        .firmbankingRequestUuid(firmbankingRequestUuid.firmbankingRequestUuid)
        .build();
  }

  public record FirmBankingRequestId(String firmBankingRequestId) {
  }

  public record FromBankType(BankType fromBankType) {
  }

  public record FromBankAccountNumber(String fromBankAccountNumber) {
  }

  public record ToBankType(BankType toBankType) {
  }

  public record ToBankAccountNumber(String toBankAccountNumber) {
  }

  public record MoneyAmount(Money moneyAmount) {
  }

  public record DomainFirmBankingStatus(FirmbankingStatus firmBankingStatus) {
  }

  public record FirmbankingRequestUuid(String firmbankingRequestUuid) {
  }

}
