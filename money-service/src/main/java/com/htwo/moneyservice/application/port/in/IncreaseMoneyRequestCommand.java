package com.htwo.moneyservice.application.port.in;

import com.htwo.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class IncreaseMoneyRequestCommand extends SelfValidating<IncreaseMoneyRequestCommand> {

  @NotBlank
  private final String targetMembershipId;
  @NotBlank
  private final String moneyAmount;

  @Builder
  private IncreaseMoneyRequestCommand(
      String targetMembershipId,
      String moneyAmount
  ) {
    this.targetMembershipId = targetMembershipId;
    this.moneyAmount = moneyAmount;

    this.validateSelf();
  }
}
