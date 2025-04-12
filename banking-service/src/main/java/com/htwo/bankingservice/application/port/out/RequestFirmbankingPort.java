package com.htwo.bankingservice.application.port.out;

import com.htwo.bankingservice.domain.FirmbankingRequest;
import com.htwo.bankingservice.domain.FirmbankingRequest.AggregateIdentifier;
import com.htwo.bankingservice.domain.FirmbankingRequest.DomainFirmBankingStatus;
import com.htwo.bankingservice.domain.FirmbankingRequest.FromBankAccountNumber;
import com.htwo.bankingservice.domain.FirmbankingRequest.FromBankType;
import com.htwo.bankingservice.domain.FirmbankingRequest.MoneyAmount;
import com.htwo.bankingservice.domain.FirmbankingRequest.ToBankAccountNumber;
import com.htwo.bankingservice.domain.FirmbankingRequest.ToBankType;
import com.htwo.bankingservice.domain.FirmbankingStatus;
import java.util.UUID;

public interface RequestFirmbankingPort {

  FirmbankingRequest createRequestFirmbanking(
      FromBankType fromBankType,
      FromBankAccountNumber fromBankAccountNumber,
      ToBankType toBankType,
      ToBankAccountNumber toBankAccountNumber,
      MoneyAmount moneyAmount,
      DomainFirmBankingStatus firmBankingStatus,
      UUID firmbankingRequestUuid,
      AggregateIdentifier aggregateIdentifier
  );

  FirmbankingRequest modifyRequestFirmbankingStatus(
      FirmbankingRequest firmbankingRequest,
      FirmbankingStatus updateFirmbankingStatus
  );

}
