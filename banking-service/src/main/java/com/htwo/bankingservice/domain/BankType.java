package com.htwo.bankingservice.domain;

import java.util.stream.Stream;
import lombok.Getter;

@Getter
public enum BankType {
  KB("국민은행"),
  SHINHAN("신한은행"),
  WOORI("우리은행"),
  HANA("하나은행"),
  IBK("기업은행"),
  NH("농협은행"),
  BUSAN("부산은행"),
  DAEGU("대구은행"),
  KYONGNAM("경남은행"),
  GWANGJU("광주은행"),
  JEONBUK("전북은행"),
  SUHYUP("수협은행"),
  CITI("씨티은행"),
  KAKAO("카카오뱅크"),
  KBANK("케이뱅크"),
  TOSS("토스뱅크");

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
