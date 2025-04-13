package com.htwo.bankingservice.application.port.out;

import com.htwo.bankingservice.domain.FirmbankingRequest;
import com.htwo.bankingservice.domain.FirmbankingRequest.AggregateIdentifier;
import com.htwo.bankingservice.domain.FirmbankingRequest.DomainFirmBankingStatus;
import com.htwo.bankingservice.domain.FirmbankingRequest.FirmbankingRequestUuid;
import com.htwo.bankingservice.domain.FirmbankingRequest.FromBankAccountNumber;
import com.htwo.bankingservice.domain.FirmbankingRequest.FromBankName;
import com.htwo.bankingservice.domain.FirmbankingRequest.MoneyAmount;
import com.htwo.bankingservice.domain.FirmbankingRequest.ToBankAccountNumber;
import com.htwo.bankingservice.domain.FirmbankingRequest.ToBankName;
import com.htwo.bankingservice.domain.FirmbankingStatus;

public interface RequestFirmbankingPort {

  FirmbankingRequest createRequestFirmbanking(
      FromBankName fromBankName,
      FromBankAccountNumber fromBankAccountNumber,
      ToBankName toBankName,
      ToBankAccountNumber toBankAccountNumber,
      MoneyAmount moneyAmount,
      DomainFirmBankingStatus firmBankingStatus,
      FirmbankingRequestUuid firmbankingRequestUuid,
      AggregateIdentifier aggregateIdentifier
  );

  FirmbankingRequest modifyRequestFirmbankingStatus(
      FirmbankingRequest firmbankingRequest,
      FirmbankingStatus updateFirmbankingStatus
  );

}
