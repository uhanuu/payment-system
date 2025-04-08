package com.htwo.moneyservice.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

/**
 * 고객의 지갑같은 개념
 */
@Getter
public class MemberMoney {

  private final String memberMoneyId;
  private final String membershipId;
  // 잔액
  private final Money balance;
  private final String moneyAggregateIdentifier;
  // TODO: 따로 linkedBankAccount 구현하기
  // private final boolean linkedBankAccount;

  @Builder(access = AccessLevel.PRIVATE)
  private MemberMoney(
      String memberMoneyId,
      String membershipId,
      Money balance,
      String moneyAggregateIdentifier
  ) {
    this.memberMoneyId = memberMoneyId;
    this.membershipId = membershipId;
    this.balance = balance;
    this.moneyAggregateIdentifier = moneyAggregateIdentifier;
  }

  public static MemberMoney generateMemberMoney(
      MemberMoneyId memberMoneyId,
      MembershipId membershipId,
      Balance balance,
      MoneyAggregateIdentifier moneyAggregateIdentifier
  ) {
    return MemberMoney.builder()
        .memberMoneyId(memberMoneyId.memberMoneyId)
        .membershipId(membershipId.membershipId)
        .balance(balance.balance)
        .moneyAggregateIdentifier(moneyAggregateIdentifier.moneyAggregateIdentifier)
        .build();
  }

  public record MemberMoneyId(String memberMoneyId) {
  }

  public record MembershipId(String membershipId) {
  }

  public record Balance(Money balance) {
  }

  public record MoneyAggregateIdentifier(String moneyAggregateIdentifier) {
  }

}
