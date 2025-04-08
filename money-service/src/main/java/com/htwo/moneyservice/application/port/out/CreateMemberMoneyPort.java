package com.htwo.moneyservice.application.port.out;

import com.htwo.moneyservice.domain.MemberMoney;

public interface CreateMemberMoneyPort {
  void createMemberMoney(
      MemberMoney.MembershipId memberId,
      MemberMoney.MoneyAggregateIdentifier aggregateIdentifier
  );
}
