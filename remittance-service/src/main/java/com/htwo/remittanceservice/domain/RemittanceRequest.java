package com.htwo.remittanceservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RemittanceRequest {
  private final String remittanceRequestId;
  private final String remittanceFromMembershipId;
  private final String toMembershipId;
  private final String toBankName;
  private final String toBankAccountNumber;
  private final RemittanceType remittanceType;
  private final Money amount;
  private RemittanceStatus remittanceStatus;

  @Builder
  private RemittanceRequest(
      String remittanceRequestId,
      String remittanceFromMembershipId,
      String toMembershipId,
      String toBankName,
      String toBankAccountNumber,
      RemittanceType remittanceType,
      Money amount,
      RemittanceStatus remittanceStatus
  ) {
    this.remittanceRequestId = remittanceRequestId;
    this.remittanceFromMembershipId = remittanceFromMembershipId;
    this.toMembershipId = toMembershipId;
    this.toBankName = toBankName;
    this.toBankAccountNumber = toBankAccountNumber;
    this.remittanceType = remittanceType;
    this.amount = amount;
    this.remittanceStatus = remittanceStatus;
  }

  public static RemittanceRequest generateRemittanceRequest(
      RemittanceRequestId remittanceRequestId,
      RemittanceFromMembershipId remittanceFromMembershipId,
      ToMembershipId toMembershipId,
      ToBankName toBankName,
      ToBankAccountNumber toBankAccountNumber,
      DomainRemittanceType remittanceType,
      Amount amount,
      DomainRemittanceStatus remittanceRequestStatus
  ) {
    return RemittanceRequest.builder()
        .remittanceRequestId(remittanceRequestId.remittanceRequestId)
        .remittanceFromMembershipId(remittanceFromMembershipId.remittanceFromMembershipId)
        .toMembershipId(toMembershipId.toMembershipId)
        .toBankName(toBankName.toBankName)
        .toBankAccountNumber(toBankAccountNumber.toBankAccountNumber)
        .remittanceType(remittanceType.remittanceType)
        .amount(amount.amount)
        .remittanceStatus(remittanceRequestStatus.remittanceStatus)
        .build();
  }

  public record RemittanceRequestId(String remittanceRequestId) {
  }

  public record RemittanceFromMembershipId(String remittanceFromMembershipId) {
  }

  public record ToMembershipId(String toMembershipId) {
  }

  public record ToBankName(String toBankName) {
  }

  public record ToBankAccountNumber(String toBankAccountNumber) {
  }

  public record DomainRemittanceType(RemittanceType remittanceType) {
  }

  public record Amount(Money amount) {
  }

  public record DomainRemittanceStatus(RemittanceStatus remittanceStatus) {
  }

  public void updateStatus(RemittanceStatus remittanceStatus) {
    this.remittanceStatus = remittanceStatus;
  }
}