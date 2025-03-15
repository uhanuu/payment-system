package com.htwo.bankingservice.application.port.in;

import com.htwo.bankingservice.domain.BankType;
import com.htwo.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class RegisterBankAccountCommand extends SelfValidating<RegisterBankAccountCommand> {

  @NotBlank
  private final String membershipId;
  @NotBlank
  private final String bankAccountNumber;
  @NotNull
  private final BankType bankType;
  private final boolean linkedStatusIsValid;

  @Builder
  private RegisterBankAccountCommand(
      String membershipId,
      String bankAccountNumber,
      BankType bankType,
      boolean linkedStatusIsValid
  ) {
    this.membershipId = membershipId;
    this.bankAccountNumber = bankAccountNumber;
    this.bankType = bankType;
    this.linkedStatusIsValid = linkedStatusIsValid;

    this.validateSelf();
  }
}
