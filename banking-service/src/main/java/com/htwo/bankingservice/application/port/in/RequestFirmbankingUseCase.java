package com.htwo.bankingservice.application.port.in;

import com.htwo.bankingservice.domain.FirmbankingRequest;

public interface RequestFirmbankingUseCase {
  void requestFirmBanking(RequestFirmbankingCommand command);
}
