package com.htwo.remittanceservice.domain;


public enum RemittanceStatus {
  REQUESTED("송금 요청됨"),
  IN_PROGRESS("송금 진행 중"),
  COMPLETED("송금 완료됨"),
  FAILED("송금 실패");

  private final String description;

  RemittanceStatus(String description) {
    this.description = description;
  }
}

