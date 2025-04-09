package com.htwo.bankingservice.adapter.axon.event;

import com.htwo.bankingservice.domain.BankType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestFirmBankingCreatedEvent {

  private BankType fromBankType;
  private String fromBankAccountNumber;
  private BankType toBankType;
  private String toBankAccountNumber;
  private String moneyAmount;
}