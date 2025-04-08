package com.htwo.moneyservice.application.port.out;

import com.htwo.moneyservice.domain.MemberMoney;

public interface GetMemberMoneyPort {
  MemberMoney getMemberMoney(
      MemberMoney.MembershipId membershipId
  );
}
