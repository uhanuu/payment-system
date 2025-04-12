package com.htwo.bankingservice.adapter.out.persistence;

import com.htwo.bankingservice.application.port.out.FindRequestFirmbankingPort;
import com.htwo.bankingservice.application.port.out.RequestFirmbankingPort;
import com.htwo.bankingservice.domain.FirmbankingRequest;
import com.htwo.bankingservice.domain.FirmbankingRequest.AggregateIdentifier;
import com.htwo.bankingservice.domain.FirmbankingRequest.DomainFirmBankingStatus;
import com.htwo.bankingservice.domain.FirmbankingRequest.FromBankAccountNumber;
import com.htwo.bankingservice.domain.FirmbankingRequest.FromBankType;
import com.htwo.bankingservice.domain.FirmbankingRequest.MoneyAmount;
import com.htwo.bankingservice.domain.FirmbankingRequest.ToBankAccountNumber;
import com.htwo.bankingservice.domain.FirmbankingRequest.ToBankType;
import com.htwo.bankingservice.domain.FirmbankingStatus;
import com.htwo.common.PersistenceAdapter;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class FirmbankingRequestPersistenceAdapter implements RequestFirmbankingPort, FindRequestFirmbankingPort {

  private final RequestFirmbankingJpaRepository requestFirmBankingJpaRepository;
  private final RequestFirmbankingMapper mapper;

  @Override
  public FirmbankingRequest createRequestFirmbanking(
      FromBankType fromBankType,
      FromBankAccountNumber fromBankAccountNumber,
      ToBankType toBankType,
      ToBankAccountNumber toBankAccountNumber,
      MoneyAmount moneyAmount,
      DomainFirmBankingStatus firmBankingStatus,
      UUID firmbankingRequestUuid,
      AggregateIdentifier aggregateIdentifier
  ) {
    final FirmbankingRequestJpaEntity firmBankingRequestJpaEntity = FirmbankingRequestJpaEntity.builder()
        .fromBankType(fromBankType.fromBankType())
        .fromBankAccountNumber(fromBankAccountNumber.fromBankAccountNumber())
        .toBankType(toBankType.toBankType())
        .toBankAccountNumber(toBankAccountNumber.toBankAccountNumber())
        .moneyAmount(moneyAmount.moneyAmount())
        .firmBankingStatus(firmBankingStatus.firmBankingStatus())
        .firmbankingRequestUuid(firmbankingRequestUuid.toString())
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
