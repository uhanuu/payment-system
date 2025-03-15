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
  private final BankType bankType;
  private final boolean linkedStatusIsValid;

  @Builder(access = AccessLevel.PRIVATE)
  private RegisteredBankAccount(
      String registeredBankAccountId,
      String membershipId,
      String bankAccountNumber,
      BankType bankType,
      boolean linkedStatusIsValid
  ) {
    this.registeredBankAccountId = registeredBankAccountId;
    this.membershipId = membershipId;
    this.bankAccountNumber = bankAccountNumber;
    this.bankType = bankType;
    this.linkedStatusIsValid = linkedStatusIsValid;
  }

  public static RegisteredBankAccount generateBankAccount(
      RegisteredBankAccountId registeredBankAccountId,
      MembershipId membershipId,
      BankAccountNumber bankAccountNumber,
      BankType bankType,
      LinkedStatusIsValid linkedStatusIsValid
  ) {
    return RegisteredBankAccount.builder()
        .registeredBankAccountId(registeredBankAccountId.registeredBankAccount)
        .membershipId(membershipId.membershipId)
        .bankAccountNumber(bankAccountNumber.bankAccountNumber)
        .bankType(bankType)
        .linkedStatusIsValid(linkedStatusIsValid.linkedStatusIsValid)
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
  public static class LinkedStatusIsValid {
    boolean linkedStatusIsValid;

    public LinkedStatusIsValid(boolean value) {
      this.linkedStatusIsValid = value;
    }
  }
}
