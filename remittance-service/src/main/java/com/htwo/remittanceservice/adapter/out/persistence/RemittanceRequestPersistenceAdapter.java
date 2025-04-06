package com.htwo.remittanceservice.adapter.out.persistence;

import com.htwo.common.PersistenceAdapter;
import com.htwo.remittanceservice.application.port.in.RequestRemittanceCommand;
import com.htwo.remittanceservice.application.port.out.RequestRemittancePort;
import com.htwo.remittanceservice.domain.Money;
import com.htwo.remittanceservice.domain.RemittanceRequest;
import com.htwo.remittanceservice.domain.RemittanceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
class RemittanceRequestPersistenceAdapter implements RequestRemittancePort {

  private final RemittanceRequestJpaRepository remittanceRequestJpaRepository;
  private final RemittanceRequestMapper mapper;

  @Override
  public RemittanceRequest createRemittanceRequestHistory(RequestRemittanceCommand command) {
    RemittanceRequestJpaEntity entity = RemittanceRequestJpaEntity.builder()
        .fromMembershipId(command.getFromMembershipId())
        .toMembershipId(command.getToMembershipId())
        .toBankName(command.getToBankName())
        .toBankAccountNumber(command.getToBankAccountNumber())
        .amount(new Money(command.getAmount()))
        .remittanceType(command.getRemittanceType())
        .remittanceStatus(RemittanceStatus.REQUESTED)
        .build();

    remittanceRequestJpaRepository.save(entity);
    return mapper.mapToDomainEntity(entity);
  }

  @Override
  public boolean saveRemittanceRequestHistory(RemittanceRequest remittanceRequest) {
    RemittanceRequestJpaEntity entity = mapper.mapToJpaEntity(remittanceRequest);
    remittanceRequestJpaRepository.save(entity);
    return true;
  }

}