package com.htwo.moneyservice.adapter.axon.event;

import com.htwo.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberMoneyCreatedEvent extends SelfValidating<MemberMoneyCreatedEvent> {

  @NotNull
  private String targetMembershipId;

  public MemberMoneyCreatedEvent(String targetMembershipId) {
    this.targetMembershipId = targetMembershipId;

    this.validateSelf();
  }
}
