package com.htwo.moneyservice.application.port.in;

import com.htwo.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
public class CreateMemberMoneyRequestCommand extends SelfValidating<CreateMemberMoneyRequestCommand> {

  @NotNull
  private final String targetMembershipId;

  public CreateMemberMoneyRequestCommand(String targetMembershipId) {
    this.targetMembershipId = targetMembershipId;

    this.validateSelf();
  }
}