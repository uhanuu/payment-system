package com.htwo.bankingservice.domain;

import lombok.Getter;

@Getter
public enum FirmbankingStatus {
  REQUESTED("요청됨"),
  PROCESSING("처리 중"),
  COMPLETED("완료"),
  FAILED("실패"),
  CANCELED("취소됨");

  private final String description;

  FirmbankingStatus(String description) {
    this.description = description;
  }
}
