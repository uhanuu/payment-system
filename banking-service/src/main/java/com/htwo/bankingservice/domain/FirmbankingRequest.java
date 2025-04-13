package com.htwo.bankingservice.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FirmbankingRequest {

  private final String firmBankingRequestId;
  private final String fromBankName;
  private final String fromBankAccountNumber;
  private final String toBankName;
  private final String toBankAccountNumber;
  private final String moneyAmount;
  private final FirmbankingStatus firmBankingStatus;
  private final String firmbankingRequestUuid;
  private final String aggregateIdentifier;

  @Builder(access = AccessLevel.PRIVATE)
  private FirmbankingRequest(
      String firmBankingRequestId,
      String fromBankName,
      String fromBankAccountNumber,
      String toBankName,
      String toBankAccountNumber,
      String moneyAmount,
      FirmbankingStatus firmBankingStatus,
      String firmbankingRequestUuid,
      String aggregateIdentifier
  ) {
    this.firmBankingRequestId = firmBankingRequestId;
    this.fromBankName = fromBankName;
    this.fromBankAccountNumber = fromBankAccountNumber;
    this.toBankName = toBankName;
    this.toBankAccountNumber = toBankAccountNumber;
    this.moneyAmount = moneyAmount;
    this.firmBankingStatus = firmBankingStatus;
    this.firmbankingRequestUuid = firmbankingRequestUuid;
    this.aggregateIdentifier = aggregateIdentifier;
  }


  public static FirmbankingRequest generateFirmbankingRequest(
      FirmBankingRequestId firmBankingRequestId,
      FromBankName fromBankName,
      FromBankAccountNumber fromBankAccountNumber,
      ToBankName toBankName,
      ToBankAccountNumber toBankAccountNumber,
      MoneyAmount moneyAmount,
      DomainFirmBankingStatus firmBankingStatus,
      FirmbankingRequestUuid firmbankingRequestUuid,
      AggregateIdentifier aggregateIdentifier
  ) {
    return FirmbankingRequest.builder()
        .firmBankingRequestId(firmBankingRequestId.firmBankingRequestId)
        .fromBankName(fromBankName.fromBankName)
        .fromBankAccountNumber(fromBankAccountNumber.fromBankAccountNumber)
        .toBankName(toBankName.toBankName)
        .toBankAccountNumber(toBankAccountNumber.toBankAccountNumber)
        .moneyAmount(moneyAmount.moneyAmount)
        .firmBankingStatus(firmBankingStatus.firmBankingStatus)
        .firmbankingRequestUuid(firmbankingRequestUuid.firmbankingRequestUuid)
        .aggregateIdentifier(aggregateIdentifier.aggregateIdentifier)
        .build();
  }

  public record FirmBankingRequestId(String firmBankingRequestId) {
  }

  public record FromBankName(String fromBankName) {
  }

  public record FromBankAccountNumber(String fromBankAccountNumber) {
  }

  public record ToBankName(String toBankName) {
  }

  public record ToBankAccountNumber(String toBankAccountNumber) {
  }

  public record MoneyAmount(String moneyAmount) {
  }

  public record DomainFirmBankingStatus(FirmbankingStatus firmBankingStatus) {
  }

  public record FirmbankingRequestUuid(String firmbankingRequestUuid) {
  }

  public record AggregateIdentifier(String aggregateIdentifier) {
  }

}
