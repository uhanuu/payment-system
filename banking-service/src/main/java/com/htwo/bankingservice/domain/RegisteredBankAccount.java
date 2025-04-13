package com.htwo.bankingservice.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Getter
public class RegisteredBankAccount {

  private final String registeredBankAccountId;
  private final String membershipId;
  private final String bankAccountNumber;
  private final String bankName;
  private final boolean linkedStatusIsValid;
  private final String aggregateIdentifier;

  @Builder(access = AccessLevel.PRIVATE)
  private RegisteredBankAccount(
      String registeredBankAccountId,
      String membershipId,
      String bankAccountNumber,
      String bankName,
      boolean linkedStatusIsValid,
      String aggregateIdentifier
  ) {
    this.registeredBankAccountId = registeredBankAccountId;
    this.membershipId = membershipId;
    this.bankAccountNumber = bankAccountNumber;
    this.bankName = bankName;
    this.linkedStatusIsValid = linkedStatusIsValid;
    this.aggregateIdentifier = aggregateIdentifier;
  }

  public static RegisteredBankAccount generateBankAccount(
      RegisteredBankAccountId registeredBankAccountId,
      MembershipId membershipId,
      BankAccountNumber bankAccountNumber,
      BankName bankName,
      LinkedStatusIsValid linkedStatusIsValid,
      AggregateIdentifier aggregateIdentifier
  ) {
    return RegisteredBankAccount.builder()
        .registeredBankAccountId(registeredBankAccountId.registeredBankAccount)
        .membershipId(membershipId.membershipId)
        .bankAccountNumber(bankAccountNumber.bankAccountNumber)
        .bankName(bankName.bankName)
        .linkedStatusIsValid(linkedStatusIsValid.linkedStatusIsValid)
        .aggregateIdentifier(aggregateIdentifier.aggregateIdentifier)
        .build();
  }

  @Value
  public static class RegisteredBankAccountId {
    String registeredBankAccount;

    public RegisteredBankAccountId(String value) {
      this.registeredBankAccount = value;
    }
  }

  @Value
  public static class MembershipId {
    String membershipId;

    public MembershipId(String value) {
      this.membershipId = value;
    }
  }

  @Value
  public static class BankAccountNumber {
    String bankAccountNumber;

    public BankAccountNumber(String value) {
      this.bankAccountNumber = value;
    }
  }

  @Value
  public static class BankName {
    String bankName;

    public BankName(String value) {
      this.bankName = value;
    }
  }

  @Value
  public static class LinkedStatusIsValid {
    boolean linkedStatusIsValid;

    public LinkedStatusIsValid(boolean value) {
      this.linkedStatusIsValid = value;
    }
  }

  @Value
  public static class AggregateIdentifier {
    String aggregateIdentifier;

    public AggregateIdentifier(String value) {
      this.aggregateIdentifier = value;
    }
  }
}
