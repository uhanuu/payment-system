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
public class RequestFirmbankingCommand extends SelfValidating<RequestFirmbankingCommand> {

  @NotNull
  private final BankType fromBankType;
  @NotBlank
  private final String fromBankAccountNumber;
  @NotNull
  private final BankType toBankType;
  @NotBlank
  private final String toBankAccountNumber;
  @NotBlank
  private final String moneyAmount;

  @Builder
  private RequestFirmbankingCommand(
      BankType fromBankType,
      String fromBankAccountNumber,
      BankType toBankType,
      String toBankAccountNumber,
      String moneyAmount
  ) {
    this.fromBankType = fromBankType;
    this.fromBankAccountNumber = fromBankAccountNumber;
    this.toBankType = toBankType;
    this.toBankAccountNumber = toBankAccountNumber;
    this.moneyAmount = moneyAmount;

    this.validateSelf();
  }
}
