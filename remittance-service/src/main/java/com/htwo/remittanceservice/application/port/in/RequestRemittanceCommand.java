package com.htwo.remittanceservice.application.port.in;

import com.htwo.common.SelfValidating;
import com.htwo.remittanceservice.domain.RemittanceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RequestRemittanceCommand extends SelfValidating<RequestRemittanceCommand> {

  @NotBlank
  private String fromMembershipId;
  @NotBlank
  @NotNull
  private String toMembershipId;
  @NotBlank
  private String toBankName;
  @NotBlank
  private String toBankAccountNumber;
  @NotNull
  private RemittanceType remittanceType;
  @NotBlank
  private String amount;

  @Builder
  private RequestRemittanceCommand(
      String fromMembershipId,
      String toMembershipId,
      String toBankName,
      String toBankAccountNumber,
      RemittanceType remittanceType,
      String amount
  ) {
    this.fromMembershipId = fromMembershipId;
    this.toMembershipId = toMembershipId;
    this.toBankName = toBankName;
    this.toBankAccountNumber = toBankAccountNumber;
    this.remittanceType = remittanceType;
    this.amount = amount;

    this.validateSelf();
  }
}