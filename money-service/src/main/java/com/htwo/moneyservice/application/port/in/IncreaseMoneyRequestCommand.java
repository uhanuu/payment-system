package com.htwo.moneyservice.application.port.in;

import com.htwo.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@EqualsAndHashCode(callSuper = false)
public class IncreaseMoneyRequestCommand extends SelfValidating<IncreaseMoneyRequestCommand> {

  @NotBlank
  private final String targetMembershipId;
  @NotBlank
  private final String moneyAmount;
  @NotBlank
  @TargetAggregateIdentifier
  private final String aggregateIdentifier;

  @Builder
  private IncreaseMoneyRequestCommand(
      String targetMembershipId,
      String moneyAmount,
      String aggregateIdentifier
  ) {
    this.targetMembershipId = targetMembershipId;
    this.moneyAmount = moneyAmount;
    this.aggregateIdentifier = aggregateIdentifier;

    this.validateSelf();
  }
}
