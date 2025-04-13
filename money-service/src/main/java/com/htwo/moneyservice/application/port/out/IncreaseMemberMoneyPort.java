package com.htwo.moneyservice.application.port.out;

import com.htwo.moneyservice.adapter.out.persistence.MemberMoneyJpaEntity;
import com.htwo.moneyservice.domain.MemberMoney.MembershipId;
import com.htwo.moneyservice.domain.Money;

public interface IncreaseMemberMoneyPort {
  MemberMoneyJpaEntity increaseMemberMoney(
      MembershipId memberId,
      Money increaseMoneyAmount
  );
}
