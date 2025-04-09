package com.htwo.bankingservice.adapter.out.persistence;

import com.htwo.bankingservice.application.port.out.RequestFirmbankingPort;
import com.htwo.bankingservice.domain.FirmbankingRequest.AggregateIdentifier;
import com.htwo.bankingservice.domain.FirmbankingRequest.DomainFirmBankingStatus;
import com.htwo.bankingservice.domain.FirmbankingRequest.FromBankAccountNumber;
import com.htwo.bankingservice.domain.FirmbankingRequest.FromBankType;
import com.htwo.bankingservice.domain.FirmbankingRequest.MoneyAmount;
import com.htwo.bankingservice.domain.FirmbankingRequest.ToBankAccountNumber;
import com.htwo.bankingservice.domain.FirmbankingRequest.ToBankType;
import com.htwo.common.PersistenceAdapter;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class FirmbankingRequestPersistenceAdapter implements RequestFirmbankingPort {

  private final RequestFirmbankingJpaRepository requestFirmBankingJpaRepository;

  @Override
  public FirmbankingRequestJpaEntity createRequestFirmbanking(
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

    return requestFirmBankingJpaRepository.save(firmBankingRequestJpaEntity);
  }

  @Override
  public FirmbankingRequestJpaEntity modifyRequestFirmbankingStatus(
      FirmbankingRequestJpaEntity updateFirmbankingRequestJpaEntity
  ) {
    // TODO: Service 단에서 변경감지를 이용해서 처리할 수 있지만 핵사고날 아키텍처에 맞게 Port를 통해서 Update
    return requestFirmBankingJpaRepository.save(updateFirmbankingRequestJpaEntity);
  }
}
