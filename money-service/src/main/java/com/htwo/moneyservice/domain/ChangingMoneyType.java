package com.htwo.moneyservice.domain;

import lombok.Getter;

@Getter
public enum ChangingMoneyType {
  INCREASE("증액"),
  DECREASE("감액");

  private final String description;

  ChangingMoneyType(String description) {
    this.description = description;
  }
}
