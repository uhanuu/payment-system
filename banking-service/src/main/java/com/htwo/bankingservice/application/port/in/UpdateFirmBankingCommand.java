package com.htwo.bankingservice.application.port.in;

import com.htwo.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateFirmBankingCommand extends SelfValidating<UpdateFirmBankingCommand> {

  @NotBlank
  private final String firmBankingAggregateIdentifier;
  @NotBlank
  private final String firmBankingStatus;

  public UpdateFirmBankingCommand(
      String firmBankingAggregateIdentifier,
      String firmBankingStatus
  ) {
    this.firmBankingAggregateIdentifier = firmBankingAggregateIdentifier;
    this.firmBankingStatus = firmBankingStatus;

    this.validateSelf();
  }
}