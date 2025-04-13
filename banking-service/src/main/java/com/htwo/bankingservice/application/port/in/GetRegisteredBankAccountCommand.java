package com.htwo.bankingservice.application.port.in;

import com.htwo.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class GetRegisteredBankAccountCommand extends
    SelfValidating<GetRegisteredBankAccountCommand> {

  @NotNull
  private final String membershipId;

  public GetRegisteredBankAccountCommand(String membershipId) {
    this.membershipId = membershipId;

    this.validateSelf();
  }
}
