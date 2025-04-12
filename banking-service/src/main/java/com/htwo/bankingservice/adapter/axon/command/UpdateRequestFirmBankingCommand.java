package com.htwo.bankingservice.adapter.axon.command;

import com.htwo.bankingservice.domain.FirmbankingStatus;
import com.htwo.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateRequestFirmBankingCommand extends SelfValidating<UpdateRequestFirmBankingCommand> {

  @NotNull
  @NotBlank
  @TargetAggregateIdentifier
  private String aggregateIdentifier;
  @NotNull
  private final FirmbankingStatus firmBankingStatus;

  public UpdateRequestFirmBankingCommand(
      String aggregateIdentifier,
      FirmbankingStatus firmBankingStatus
  ) {
    this.aggregateIdentifier = aggregateIdentifier;
    this.firmBankingStatus = firmBankingStatus;

    this.validateSelf();
  }
}