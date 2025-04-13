package com.htwo.bankingservice.application.port.in;

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
  private final String fromBankName;
  @NotBlank
  private final String fromBankAccountNumber;
  @NotNull
  private final String toBankName;
  @NotBlank
  private final String toBankAccountNumber;
  @NotBlank
  private final String moneyAmount;

  @Builder
  private RequestFirmbankingCommand(
      String fromBankName,
      String fromBankAccountNumber,
      String toBankName,
      String toBankAccountNumber,
      String moneyAmount
  ) {
    this.fromBankName = fromBankName;
    this.fromBankAccountNumber = fromBankAccountNumber;
    this.toBankName = toBankName;
    this.toBankAccountNumber = toBankAccountNumber;
    this.moneyAmount = moneyAmount;

    this.validateSelf();
  }
}
