package com.htwo.bankingservice.adapter.out.persistence;

import com.htwo.bankingservice.domain.FirmbankingRequest;
import com.htwo.bankingservice.domain.FirmbankingRequest.AggregateIdentifier;
import com.htwo.bankingservice.domain.FirmbankingRequest.DomainFirmBankingStatus;
import com.htwo.bankingservice.domain.FirmbankingRequest.FirmBankingRequestId;
import com.htwo.bankingservice.domain.FirmbankingRequest.FirmbankingRequestUuid;
import com.htwo.bankingservice.domain.FirmbankingRequest.FromBankAccountNumber;
import com.htwo.bankingservice.domain.FirmbankingRequest.FromBankType;
import com.htwo.bankingservice.domain.FirmbankingRequest.MoneyAmount;
import com.htwo.bankingservice.domain.FirmbankingRequest.ToBankAccountNumber;
import com.htwo.bankingservice.domain.FirmbankingRequest.ToBankType;
import org.springframework.stereotype.Component;

@Component
public class RequestFirmbankingMapper {

  public FirmbankingRequest mapToDomainEntity(FirmbankingRequestJpaEntity firmBankingRequestJpaEntity) {
    return FirmbankingRequest.generateFirmbankingRequest(
        new FirmBankingRequestId(String.valueOf(firmBankingRequestJpaEntity.getFirmBankingRequestId())),
        new FromBankType(firmBankingRequestJpaEntity.getFromBankType()),
        new FromBankAccountNumber(firmBankingRequestJpaEntity.getFromBankAccountNumber()),
        new ToBankType(firmBankingRequestJpaEntity.getToBankType()),
        new ToBankAccountNumber(firmBankingRequestJpaEntity.getToBankAccountNumber()),
        new MoneyAmount(firmBankingRequestJpaEntity.getMoneyAmount()),
        new DomainFirmBankingStatus(firmBankingRequestJpaEntity.getFirmBankingStatus()),
        new FirmbankingRequestUuid(firmBankingRequestJpaEntity.getFirmbankingRequestUuid()),
        new AggregateIdentifier(firmBankingRequestJpaEntity.getAggregateIdentifier())
    );
  }

  public FirmbankingRequestJpaEntity mapToJpaEntity(FirmbankingRequest firmBankingRequestJpaEntity) {
    return FirmbankingRequestJpaEntity.builder()
        .firmBankingRequestId(Long.parseLong(firmBankingRequestJpaEntity.getFirmBankingRequestId()))
        .fromBankType(firmBankingRequestJpaEntity.getFromBankType())
        .fromBankAccountNumber(firmBankingRequestJpaEntity.getFromBankAccountNumber())
        .toBankType(firmBankingRequestJpaEntity.getToBankType())
        .toBankAccountNumber(firmBankingRequestJpaEntity.getToBankAccountNumber())
        .moneyAmount(firmBankingRequestJpaEntity.getMoneyAmount())
        .firmBankingStatus(firmBankingRequestJpaEntity.getFirmBankingStatus())
        .firmbankingRequestUuid(firmBankingRequestJpaEntity.getFirmbankingRequestUuid())
        .aggregateIdentifier(firmBankingRequestJpaEntity.getAggregateIdentifier())
        .build();
  }

}
