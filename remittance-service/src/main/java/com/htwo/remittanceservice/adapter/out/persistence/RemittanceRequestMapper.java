package com.htwo.remittanceservice.adapter.out.persistence;

import com.htwo.remittanceservice.domain.RemittanceRequest;
import org.springframework.stereotype.Component;

@Component
class RemittanceRequestMapper {
  public RemittanceRequest mapToDomainEntity(RemittanceRequestJpaEntity remittanceRequestJpaEntity) {
    return RemittanceRequest.generateRemittanceRequest(
        new RemittanceRequest.RemittanceRequestId(remittanceRequestJpaEntity.getFromMembershipId()),
        new RemittanceRequest.RemittanceFromMembershipId(remittanceRequestJpaEntity.getFromMembershipId()),
        new RemittanceRequest.ToMembershipId(remittanceRequestJpaEntity.getToMembershipId()),
        new RemittanceRequest.ToBankName(remittanceRequestJpaEntity.getToBankName()),
        new RemittanceRequest.ToBankAccountNumber(remittanceRequestJpaEntity.getToBankAccountNumber()),
        new RemittanceRequest.DomainRemittanceType(remittanceRequestJpaEntity.getRemittanceType()),
        new RemittanceRequest.Amount(remittanceRequestJpaEntity.getAmount()),
        new RemittanceRequest.DomainRemittanceStatus(remittanceRequestJpaEntity.getRemittanceStatus())
    );
  }

  public RemittanceRequestJpaEntity mapToJpaEntity(RemittanceRequest remittanceRequest) {
    return RemittanceRequestJpaEntity.builder()
        .remittanceRequestId(Long.parseLong(remittanceRequest.getRemittanceRequestId()))
        .fromMembershipId(remittanceRequest.getRemittanceFromMembershipId())
        .toMembershipId(remittanceRequest.getToMembershipId())
        .toBankName(remittanceRequest.getToBankName())
        .toBankAccountNumber(remittanceRequest.getToBankAccountNumber())
        .remittanceType(remittanceRequest.getRemittanceType())
        .amount(remittanceRequest.getAmount())
        .remittanceStatus(remittanceRequest.getRemittanceStatus())
        .build();
  }
}