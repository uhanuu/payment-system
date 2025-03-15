package com.htwo.bankingservice.application.port.in;

import com.htwo.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FindRegisteredBankAccountQuery extends SelfValidating<FindRegisteredBankAccountQuery> {
  @NotBlank
  private final String registeredBankAccountId;
  @NotBlank
  private final String membershipId;

  public FindRegisteredBankAccountQuery(
      String registeredBankAccountId,
      String membershipId
  ) {
    this.registeredBankAccountId = registeredBankAccountId;
    this.membershipId = membershipId;

    this.validateSelf();
  }
}
