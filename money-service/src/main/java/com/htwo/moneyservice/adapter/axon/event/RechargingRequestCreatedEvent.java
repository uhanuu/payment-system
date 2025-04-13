package com.htwo.moneyservice.adapter.axon.event;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RechargingRequestCreatedEvent {

  private String rechargingRequestId;
  private String membershipId;
  private int amount;
  private String bankingAccountAggregateIdentifier;
  private String bankName;
  private String bankAccountNumber;

  @Builder
  private RechargingRequestCreatedEvent(
      String rechargingRequestId,
      String membershipId,
      int amount,
      String bankingAccountAggregateIdentifier,
      String bankName,
      String bankAccountNumber
  ) {
    this.rechargingRequestId = rechargingRequestId;
    this.membershipId = membershipId;
    this.amount = amount;
    this.bankingAccountAggregateIdentifier = bankingAccountAggregateIdentifier;
    this.bankName = bankName;
    this.bankAccountNumber = bankAccountNumber;
  }
}
