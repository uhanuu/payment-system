package com.htwo.bankingservice.adapter.axon.event;

import com.htwo.bankingservice.domain.FirmbankingStatus;
import org.springframework.util.Assert;

public record UpdateRequestFirmBankingEvent(
    String aggregateIdentifier,
    FirmbankingStatus firmBankingStatus
) {
  public UpdateRequestFirmBankingEvent {
    Assert.hasText(aggregateIdentifier, "aggregateIdentifier is not blank or null");
    Assert.notNull(firmBankingStatus, "firmBankingStatus is not null");
  }
}