package com.htwo.moneyservice.adapter.out.persistence;

import com.htwo.common.PersistenceAdapter;
import com.htwo.moneyservice.application.port.out.MoneyChangingRequestPort;
import com.htwo.moneyservice.domain.MoneyChangingRequest.ChangingMoneyAmount;
import com.htwo.moneyservice.domain.MoneyChangingRequest.DomainChangingMoneyStatus;
import com.htwo.moneyservice.domain.MoneyChangingRequest.DomainChangingMoneyType;
import com.htwo.moneyservice.domain.MoneyChangingRequest.MoneyChangingRequestUuid;
import com.htwo.moneyservice.domain.MoneyChangingRequest.TargetMembershipId;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyChangingChangingRequestPersistenceAdapter implements MoneyChangingRequestPort {

  private final MoneyChangingRequestJpaRepository moneyChangingRequestJpaRepository;

  @Override
  public MoneyChangingRequestJpaEntity createMoneyChangingRequest(
      TargetMembershipId targetMembershipId,
      DomainChangingMoneyType domainChangingMoneyType,
      ChangingMoneyAmount changingMoneyAmount,
      DomainChangingMoneyStatus domainChangingMoneyStatus,
      MoneyChangingRequestUuid moneyChangingRequestUuid
  ) {
    final MoneyChangingRequestJpaEntity moneyChangingRequestJpaEntity = MoneyChangingRequestJpaEntity.builder()
        .targetMembershipId(targetMembershipId.targetMembershipId())
        .changingMoneyType(domainChangingMoneyType.changingMoneyType())
        .changingMoneyStatus(domainChangingMoneyStatus.changingMoneyStatus())
        .moneyAmount(changingMoneyAmount.changingMoneyAmount())
        .moneyChangingRequestUuid(moneyChangingRequestUuid.moneyChangingRequestUuid())
        .build();

    return moneyChangingRequestJpaRepository.save(moneyChangingRequestJpaEntity);
  }
}
