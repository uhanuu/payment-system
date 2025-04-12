package com.htwo.bankingservice.domain;

import java.util.stream.Stream;
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

  public boolean isFailed() {
    return this == FAILED;
  }

  public static FirmbankingStatus getFirmbankingStatus(String name) {
    return Stream.of(FirmbankingStatus.values())
        .filter(status -> status.name().equals(name))
        .findAny()
        .orElseThrow(IllegalArgumentException::new);
  }
}
