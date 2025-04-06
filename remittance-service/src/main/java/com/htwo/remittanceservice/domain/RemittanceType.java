package com.htwo.remittanceservice.domain;

import java.util.stream.Stream;
import lombok.Getter;

@Getter
public enum RemittanceType {

  MEMBERSHIP_ACCOUNT("INTERNAL","내부 고객"),
  EXTERNAL_BANK_ACCOUNT("EXTERNAL","외부 은행 계좌");

  private final String code;
  private final String description;

  RemittanceType(String code, String description) {
    this.code = code;
    this.description = description;
  }

  public boolean isInternalAccount() {
    return this == MEMBERSHIP_ACCOUNT;
  }

  public boolean isExternalAccount() {
    return this == EXTERNAL_BANK_ACCOUNT;
  }

  public static RemittanceType getInstance(String code) {
    return Stream.of(RemittanceType.values())
        .filter(type -> type.code.equals(code))
        .findAny()
        .orElseThrow(IllegalArgumentException::new);
  }

}
