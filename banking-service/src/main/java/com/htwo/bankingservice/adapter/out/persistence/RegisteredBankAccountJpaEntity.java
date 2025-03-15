package com.htwo.bankingservice.adapter.out.persistence;

import com.htwo.bankingservice.domain.BankType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "registered_bank_account")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RegisteredBankAccountJpaEntity {

  @Id
  @GeneratedValue
  private Long registeredBankAccountId;
  private String membershipId;
  private String bankAccountNumber;
  @Enumerated(EnumType.STRING)
  private BankType bankType;
  private boolean linkedStatusIsValid;

  @Builder
  private RegisteredBankAccountJpaEntity(
      String membershipId,
      String bankAccountNumber,
      BankType bankType,
      boolean linkedStatusIsValid
  ) {
    this.membershipId = membershipId;
    this.bankAccountNumber = bankAccountNumber;
    this.bankType = bankType;
    this.linkedStatusIsValid = linkedStatusIsValid;
  }

  public void updateBankAccountNumber(String bankAccountNumber) {
    this.bankAccountNumber = bankAccountNumber;
  }

  public void updateBankType(BankType bankType) {
    this.bankType = bankType;
  }

  public void updateLinkedStatusIsValid(boolean linkedStatusIsValid) {
    this.linkedStatusIsValid = linkedStatusIsValid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegisteredBankAccountJpaEntity that = (RegisteredBankAccountJpaEntity) o;
    return Objects.equals(registeredBankAccountId, that.registeredBankAccountId) && Objects.equals(
        membershipId, that.membershipId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(registeredBankAccountId, membershipId);
  }
}
