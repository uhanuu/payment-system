package com.htwo.moneyservice.adapter.axon.event;

import com.htwo.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IncreaseMemberMoneyEvent extends SelfValidating<IncreaseMemberMoneyEvent> {

  @NotBlank
  private String targetMembershipId;
  @NotBlank
  private String aggregateIdentifier;
  @NotBlank
  private String amount;

  public IncreaseMemberMoneyEvent(
      String targetMembershipId,
      String aggregateIdentifier,
      String amount
  ) {
    this.targetMembershipId = targetMembershipId;
    this.aggregateIdentifier = aggregateIdentifier;
    this.amount = amount;

    this.validateSelf();
  }
}
