package com.htwo.bankingservice.domain;

import lombok.Getter;

@Getter
public enum BankType {
  KB("국민은행");

  private final String name;
  BankType(String name) {
    this.name = name;
  }
}
