package com.htwo.moneyservice.application.port.out;


import com.htwo.moneyservice.adapter.out.persistence.MoneyChangingRequestJpaEntity;
import com.htwo.moneyservice.domain.MoneyChangingRequest.ChangingMoneyAmount;
import com.htwo.moneyservice.domain.MoneyChangingRequest.DomainChangingMoneyStatus;
import com.htwo.moneyservice.domain.MoneyChangingRequest.DomainChangingMoneyType;
import com.htwo.moneyservice.domain.MoneyChangingRequest.MoneyChangingRequestUuid;
import com.htwo.moneyservice.domain.MoneyChangingRequest.TargetMembershipId;

public interface MoneyChangingRequestPort {
  MoneyChangingRequestJpaEntity createMoneyChangingRequest(
      TargetMembershipId targetMembershipId,
      DomainChangingMoneyType domainChangingMoneyType,
      ChangingMoneyAmount changingMoneyAmount,
      DomainChangingMoneyStatus domainChangingMoneyStatus,
      MoneyChangingRequestUuid moneyChangingRequestUuid
  );

}
