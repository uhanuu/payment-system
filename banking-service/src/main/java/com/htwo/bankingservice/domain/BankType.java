package com.htwo.bankingservice.domain;

import java.util.Arrays;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public enum BankType {
  KB("국민은행");

  private final String name;
  BankType(String name) {
    this.name = name;
  }

  public static BankType getBankType(String bankName) {
    return Stream.of(BankType.values())
        .filter(bankType -> bankType.name.equals(bankName))
        .findAny()
        .orElseThrow(RuntimeException::new);
  }

}
