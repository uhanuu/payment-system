package com.htwo.membershipservice.application.port.in;

import com.htwo.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class FindMembershipCommand extends SelfValidating<FindMembershipCommand> {

  @NotBlank
  private final String membershipId;

  @Builder
  public FindMembershipCommand(String membershipId) {
    this.membershipId = membershipId;
    this.validateSelf();
  }
}
