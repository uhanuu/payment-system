package com.htwo.membershipservice.application.port.in;

import com.htwo.common.SelfValidating;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class RegisterMembershipCommand extends SelfValidating<RegisterMembershipCommand> {

  @NotBlank
  private final String name;
  @NotBlank
  private final String email;
  @NotBlank
  private final String address;
  @AssertTrue
  private final boolean isValid; // 고객에게 전달받는게 아니래 내부 시스템(admin)에서 생성할 때 아닐 수 있음
  private final boolean isCorp;

  @Builder
  private RegisterMembershipCommand(
      String name,
      String email,
      String address,
      boolean isValid,
      boolean isCorp
  ) {
    this.name = name;
    this.email = email;
    this.address = address;
    this.isValid = isValid;
    this.isCorp = isCorp;

    this.validateSelf(); // 어노테이션에 대해서 검증
  }
}
