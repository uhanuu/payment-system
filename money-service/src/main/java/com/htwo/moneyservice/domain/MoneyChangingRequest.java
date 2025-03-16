package com.htwo.moneyservice.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MoneyChangingRequest {

  private final String moneyChangingRequestId;
  private final String targetMembershipId;
  private final ChangingMoneyType changingMoneyType;
  private final Money changingMoneyAmount;
  private final ChangingMoneyStatus changingMoneyStatus;
  private final String moneyChangingRequestUuid;
  private final LocalDateTime createdAt;

  @Builder(access = AccessLevel.PRIVATE)
  public MoneyChangingRequest(
      String moneyChangingRequestId,
      String targetMembershipId,
      ChangingMoneyType changingMoneyType,
      Money changingMoneyAmount,
      ChangingMoneyStatus changingMoneyStatus,
      String moneyChangingRequestUuid,
      LocalDateTime createdAt
  ) {
    this.moneyChangingRequestId = moneyChangingRequestId;
    this.targetMembershipId = targetMembershipId;
    this.changingMoneyType = changingMoneyType;
    this.changingMoneyAmount = changingMoneyAmount;
    this.changingMoneyStatus = changingMoneyStatus;
    this.moneyChangingRequestUuid = moneyChangingRequestUuid;
    this.createdAt = createdAt;
  }

  public static MoneyChangingRequest generateMoneyChangingRequest(
      MoneyChangingRequestId moneyChangingRequestId,
      TargetMembershipId targetMembershipId,
      DomainChangingMoneyType domainChangingMoneyType,
      ChangingMoneyAmount changingMoneyAmount,
      DomainChangingMoneyStatus domainChangingMoneyStatus,
      MoneyChangingRequestUuid moneyChangingRequestUuid,
      CreatedAt createdAt
  ) {
    return MoneyChangingRequest.builder()
        .moneyChangingRequestId(moneyChangingRequestId.moneyChangingRequestId)
        .targetMembershipId(targetMembershipId.targetMembershipId)
        .changingMoneyType(domainChangingMoneyType.changingMoneyType)
        .changingMoneyAmount(changingMoneyAmount.changingMoneyAmount)
        .changingMoneyStatus(domainChangingMoneyStatus.changingMoneyStatus)
        .moneyChangingRequestUuid(moneyChangingRequestUuid.moneyChangingRequestUuid)
        .createdAt(createdAt.createdAt)
        .build();
  }

  public record MoneyChangingRequestId(String moneyChangingRequestId) {
  }

  public record TargetMembershipId(String targetMembershipId) {
  }

  public record DomainChangingMoneyType(ChangingMoneyType changingMoneyType) {
  }

  public record ChangingMoneyAmount(Money changingMoneyAmount) {
  }

  public record DomainChangingMoneyStatus(ChangingMoneyStatus changingMoneyStatus) {
  }

  public record MoneyChangingRequestUuid(String moneyChangingRequestUuid) {
  }

  public record CreatedAt(LocalDateTime createdAt) {
  }

}
