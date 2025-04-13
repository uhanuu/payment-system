package com.htwo.bankingservice.adapter.out.persistence;

import com.htwo.bankingservice.domain.BankType;
import com.htwo.bankingservice.domain.FirmbankingRequest;
import com.htwo.bankingservice.domain.FirmbankingRequest.AggregateIdentifier;
import com.htwo.bankingservice.domain.FirmbankingRequest.DomainFirmBankingStatus;
import com.htwo.bankingservice.domain.FirmbankingRequest.FirmBankingRequestId;
import com.htwo.bankingservice.domain.FirmbankingRequest.FirmbankingRequestUuid;
import com.htwo.bankingservice.domain.FirmbankingRequest.FromBankAccountNumber;
import com.htwo.bankingservice.domain.FirmbankingRequest.FromBankName;
import com.htwo.bankingservice.domain.FirmbankingRequest.MoneyAmount;
import com.htwo.bankingservice.domain.FirmbankingRequest.ToBankAccountNumber;
import com.htwo.bankingservice.domain.FirmbankingRequest.ToBankName;
import com.htwo.bankingservice.domain.Money;
import org.springframework.stereotype.Component;

@Component
public class RequestFirmbankingMapper {

  public FirmbankingRequest mapToDomainEntity(FirmbankingRequestJpaEntity firmBankingRequestJpaEntity) {
    return FirmbankingRequest.generateFirmbankingRequest(
        new FirmBankingRequestId(String.valueOf(firmBankingRequestJpaEntity.getFirmBankingRequestId())),
        new FromBankName(firmBankingRequestJpaEntity.getFromBankType().getName()),
        new FromBankAccountNumber(firmBankingRequestJpaEntity.getFromBankAccountNumber()),
        new ToBankName(firmBankingRequestJpaEntity.getToBankType().getName()),
        new ToBankAccountNumber(firmBankingRequestJpaEntity.getToBankAccountNumber()),
        new MoneyAmount(firmBankingRequestJpaEntity.getMoneyAmount().toStringValue()),
        new DomainFirmBankingStatus(firmBankingRequestJpaEntity.getFirmBankingStatus()),
        new FirmbankingRequestUuid(firmBankingRequestJpaEntity.getFirmbankingRequestUuid()),
        new AggregateIdentifier(firmBankingRequestJpaEntity.getAggregateIdentifier())
    );
  }

  public FirmbankingRequestJpaEntity mapToJpaEntity(FirmbankingRequest firmBankingRequestJpaEntity) {
    return FirmbankingRequestJpaEntity.builder()
        .firmBankingRequestId(Long.parseLong(firmBankingRequestJpaEntity.getFirmBankingRequestId()))
        .fromBankType(BankType.getBankType(firmBankingRequestJpaEntity.getFromBankName()))
        .fromBankAccountNumber(firmBankingRequestJpaEntity.getFromBankAccountNumber())
        .toBankType(BankType.getBankType(firmBankingRequestJpaEntity.getToBankName()))
        .toBankAccountNumber(firmBankingRequestJpaEntity.getToBankAccountNumber())
        .moneyAmount(new Money(firmBankingRequestJpaEntity.getMoneyAmount()))
        .firmBankingStatus(firmBankingRequestJpaEntity.getFirmBankingStatus())
        .firmbankingRequestUuid(firmBankingRequestJpaEntity.getFirmbankingRequestUuid())
        .aggregateIdentifier(firmBankingRequestJpaEntity.getAggregateIdentifier())
        .build();
  }

}
