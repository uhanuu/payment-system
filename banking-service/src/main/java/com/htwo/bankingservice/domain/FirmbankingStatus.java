package com.htwo.bankingservice.domain;

import lombok.Getter;

@Getter
public enum FirmbankingStatus {
  REQUEST("요청"),
  COMPLETED("완료"),
  FAILED("실패"),
  CANCELED("취소");

  private final String description;

  FirmbankingStatus(String description) {
    this.description = description;
  }
}
