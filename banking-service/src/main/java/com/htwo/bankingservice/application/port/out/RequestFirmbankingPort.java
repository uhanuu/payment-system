package com.htwo.bankingservice.application.port.out;

import com.htwo.bankingservice.adapter.out.persistence.FirmbankingRequestJpaEntity;
import com.htwo.bankingservice.domain.FirmbankingRequest.DomainFirmBankingStatus;
import com.htwo.bankingservice.domain.FirmbankingRequest.FirmBankingRequestId;
import com.htwo.bankingservice.domain.FirmbankingRequest.FromBankAccountNumber;
import com.htwo.bankingservice.domain.FirmbankingRequest.FromBankType;
import com.htwo.bankingservice.domain.FirmbankingRequest.MoneyAmount;
import com.htwo.bankingservice.domain.FirmbankingRequest.ToBankAccountNumber;
import com.htwo.bankingservice.domain.FirmbankingRequest.ToBankType;
import java.util.UUID;

public interface RequestFirmbankingPort {

  FirmbankingRequestJpaEntity createRequestFirmbanking(
      FromBankType fromBankType,
      FromBankAccountNumber fromBankAccountNumber,
      ToBankType toBankType,
      ToBankAccountNumber toBankAccountNumber,
      MoneyAmount moneyAmount,
      DomainFirmBankingStatus firmBankingStatus,
      UUID firmbankingRequestUuid
  );

  FirmbankingRequestJpaEntity modifyRequestFirmbankingStatus(FirmbankingRequestJpaEntity updateFirmbankingRequestJpaEntity);
}
