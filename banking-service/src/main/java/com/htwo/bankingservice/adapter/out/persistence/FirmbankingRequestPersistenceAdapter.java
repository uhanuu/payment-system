package com.htwo.bankingservice.adapter.out.persistence;

import com.htwo.bankingservice.application.port.out.FindRequestFirmbankingPort;
import com.htwo.bankingservice.application.port.out.RequestFirmbankingPort;
import com.htwo.bankingservice.domain.BankType;
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
import com.htwo.bankingservice.domain.Money;
import com.htwo.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class FirmbankingRequestPersistenceAdapter implements RequestFirmbankingPort, FindRequestFirmbankingPort {

  private final RequestFirmbankingJpaRepository requestFirmBankingJpaRepository;
  private final RequestFirmbankingMapper mapper;

  @Override
  public FirmbankingRequest createRequestFirmbanking(
      FromBankName fromBankName,
      FromBankAccountNumber fromBankAccountNumber,
      ToBankName toBankName,
      ToBankAccountNumber toBankAccountNumber,
      MoneyAmount moneyAmount,
      DomainFirmBankingStatus firmBankingStatus,
      FirmbankingRequestUuid firmbankingRequestUuid,
      AggregateIdentifier aggregateIdentifier
  ) {
    final FirmbankingRequestJpaEntity firmBankingRequestJpaEntity = FirmbankingRequestJpaEntity.builder()
        .fromBankType(BankType.getBankType(fromBankName.fromBankName()))
        .fromBankAccountNumber(fromBankAccountNumber.fromBankAccountNumber())
        .toBankType(BankType.getBankType(toBankName.toBankName()))
        .toBankAccountNumber(toBankAccountNumber.toBankAccountNumber())
        .moneyAmount(new Money(moneyAmount.moneyAmount()))
        .firmBankingStatus(firmBankingStatus.firmBankingStatus())
        .firmbankingRequestUuid(firmbankingRequestUuid.firmbankingRequestUuid())
        .aggregateIdentifier(aggregateIdentifier.aggregateIdentifier())
        .build();

    requestFirmBankingJpaRepository.save(firmBankingRequestJpaEntity);
    return mapper.mapToDomainEntity(firmBankingRequestJpaEntity);
  }

  @Override
  public FirmbankingRequest modifyRequestFirmbankingStatus(
      FirmbankingRequest firmbankingRequest,
      FirmbankingStatus updateFirmbankingStatus
  ) {
    FirmbankingRequestJpaEntity firmbankingRequestJpaEntity = mapper.mapToJpaEntity(firmbankingRequest);
    firmbankingRequestJpaEntity.updateFirmBankingStatus(updateFirmbankingStatus);
    requestFirmBankingJpaRepository.save(firmbankingRequestJpaEntity);

    return mapper.mapToDomainEntity(firmbankingRequestJpaEntity);
  }

  @Override
  public FirmbankingRequest getFirmBankingRequest(AggregateIdentifier aggregateIdentifier) {
    final FirmbankingRequestJpaEntity firmbankingRequestJpaEntity = requestFirmBankingJpaRepository.findByAggregateIdentifier(
            aggregateIdentifier.aggregateIdentifier())
        .orElseThrow(RuntimeException::new);

    return mapper.mapToDomainEntity(firmbankingRequestJpaEntity);
  }
}
