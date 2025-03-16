package com.htwo.bankingservice.application.port.out;

import com.htwo.bankingservice.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.htwo.bankingservice.adapter.out.external.bank.FirmbankingResult;

public interface RequestExternalFirmbankingPort {
  FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest request);
}
