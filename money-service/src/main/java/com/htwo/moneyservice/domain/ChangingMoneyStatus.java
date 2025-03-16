package com.htwo.moneyservice.domain;

import lombok.Getter;

@Getter
public enum ChangingMoneyStatus {
  REQUESTED("요청됨"),
  PROCESSING("처리 중"),
  COMPLETED("완료"),
  FAILED("실패"),
  CANCELED("취소됨");

  private final String description;

  ChangingMoneyStatus(String description) {
    this.description = description;
  }
}
