package com.htwo.common.saga.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestFirmBankingFinishedEvent {

  private String requestFirmBankingId;
  private String rechargingRequestId;
  private String membershipId;
  private String toBankName;
  private String toBankAccountNumber;
  private String moneyAmount; // only won
  private String status;
  private String requestFirmBankingAggregateIdentifier;
}
