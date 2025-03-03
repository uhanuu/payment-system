package com.htwo.membershipservice.application.port.in;

import com.htwo.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class ModifyMembershipCommand extends SelfValidating<ModifyMembershipCommand> {

  @NotBlank
  private final String membershipId;
  @NotBlank
  private final String name;
  @NotBlank
  private final String email;
  @NotBlank
  private final String address;
  @NotNull
  private final Boolean isValid;
  @NotNull
  private final Boolean isCorp;

  @Builder
  private ModifyMembershipCommand(
      String membershipId,
      String name,
      String email,
      String address,
      boolean isValid,
      boolean isCorp
  ) {
    this.membershipId = membershipId;
    this.name = name;
    this.email = email;
    this.address = address;
    this.isValid = isValid;
    this.isCorp = isCorp;

    this.validateSelf(); // 어노테이션에 대해서 검증
  }
}
