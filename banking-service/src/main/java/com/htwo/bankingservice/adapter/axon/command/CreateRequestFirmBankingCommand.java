package com.htwo.bankingservice.adapter.axon.command;

import com.htwo.bankingservice.domain.BankType;
import com.htwo.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateRequestFirmBankingCommand  extends SelfValidating<CreateRequestFirmBankingCommand> {
  @NotNull
  private BankType fromBankType;
  @NotBlank
  private String fromBankAccountNumber;
  @NotNull
  private BankType toBankType;
  @NotBlank
  private String toBankAccountNumber;
  @NotNull
  private String moneyAmount;

  public CreateRequestFirmBankingCommand(
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
