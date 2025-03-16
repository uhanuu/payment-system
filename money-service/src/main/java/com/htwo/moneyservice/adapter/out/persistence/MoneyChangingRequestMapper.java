package com.htwo.moneyservice.adapter.out.persistence;

import com.htwo.moneyservice.domain.MoneyChangingRequest;
import com.htwo.moneyservice.domain.MoneyChangingRequest.ChangingMoneyAmount;
import com.htwo.moneyservice.domain.MoneyChangingRequest.CreatedAt;
import com.htwo.moneyservice.domain.MoneyChangingRequest.DomainChangingMoneyStatus;
import com.htwo.moneyservice.domain.MoneyChangingRequest.DomainChangingMoneyType;
import com.htwo.moneyservice.domain.MoneyChangingRequest.MoneyChangingRequestId;
import com.htwo.moneyservice.domain.MoneyChangingRequest.MoneyChangingRequestUuid;
import com.htwo.moneyservice.domain.MoneyChangingRequest.TargetMembershipId;
import org.springframework.stereotype.Component;

@Component
public class MoneyChangingRequestMapper {
  public MoneyChangingRequest mapToDomainEntity(MoneyChangingRequestJpaEntity moneyChangingRequestJpaEntity) {
    return MoneyChangingRequest.generateMoneyChangingRequest(
        new MoneyChangingRequestId(String.valueOf(moneyChangingRequestJpaEntity.getMoneyChangingRequestId())),
        new TargetMembershipId(moneyChangingRequestJpaEntity.getTargetMembershipId()),
        new DomainChangingMoneyType(moneyChangingRequestJpaEntity.getChangingMoneyType()),
        new ChangingMoneyAmount(moneyChangingRequestJpaEntity.getMoneyAmount()),
        new DomainChangingMoneyStatus(moneyChangingRequestJpaEntity.getChangingMoneyStatus()),
        new MoneyChangingRequestUuid(moneyChangingRequestJpaEntity.getMoneyChangingRequestUuid()),
        new CreatedAt(moneyChangingRequestJpaEntity.getCreatedAt())
    );
  }
}
