package com.htwo.bankingservice.application.port.out;

import com.htwo.bankingservice.domain.FirmbankingRequest;
import com.htwo.bankingservice.domain.FirmbankingRequest.AggregateIdentifier;

public interface FindRequestFirmbankingPort {
  FirmbankingRequest getFirmBankingRequest(AggregateIdentifier aggregateIdentifier);
}
