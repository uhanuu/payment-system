package com.htwo.moneyservice.adapter.in.web;

import com.htwo.moneyservice.domain.ChangingMoneyType;
import com.htwo.moneyservice.domain.MoneyChangingRequest;

// TODO: UseCase에서 Domain 말고 DTO 형태로 변환해서 Domain 의존 끊어주기 + Enum 위치 고려하기(추가, 이동)
public record MoneyChangingResultDetails(
    String moneyChangingRequestId,
    ChangingMoneyType moneyChangingType,
    MoneyChangingResultStatus moneyChangingResultStatus,
    String moneyAmount
) {
  public static MoneyChangingResultDetails from(MoneyChangingRequest moneyChangingRequest) {
    MoneyChangingResultStatus moneyChangingResultStatus = getMoneyChangingResultStatus(moneyChangingRequest);

    return new MoneyChangingResultDetails(
        moneyChangingRequest.getMoneyChangingRequestId(),
        moneyChangingRequest.getChangingMoneyType(),
        moneyChangingResultStatus,
        String.valueOf(moneyChangingRequest.getChangingMoneyAmount().getMoneyAmount())
    );
  }

  private static MoneyChangingResultStatus getMoneyChangingResultStatus(MoneyChangingRequest moneyChangingRequest) {
    return switch (moneyChangingRequest.getChangingMoneyStatus()) {
      case REQUESTED -> MoneyChangingResultStatus.REQUESTED;
      case PROCESSING -> MoneyChangingResultStatus.PROCESSING;
      case COMPLETED -> MoneyChangingResultStatus.COMPLETED;
      case FAILED -> MoneyChangingResultStatus.FAILED;
      case CANCELED -> MoneyChangingResultStatus.CANCELED;
    };
  }
}
