package com.htwo.moneyservice.adapter.in.web;

import lombok.Getter;

@Getter
public enum MoneyChangingResultStatus {
  REQUESTED("요청됨"),
  PROCESSING("처리 중"),
  COMPLETED("완료"),
  FAILED_INSUFFICIENT_BALANCE("실패 - 잔액 부족"),
  FAILED_INVALID_MEMBERSHIP("실패 - 잘못된 멤버십 ID"),
  FAILED_NO_CHANGE_REQUEST("실패 - 변액 요청 없음"),
  FAILED_OTHER("실패 - 기타 사유"),
  FAILED("실패"),
  CANCELED("취소됨");

  private final String description;

  MoneyChangingResultStatus(String description) {
    this.description = description;
  }
}
