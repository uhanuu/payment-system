package com.htwo.moneyservice.adapter.out.persistence;

import com.htwo.moneyservice.domain.MemberMoney;
import com.htwo.moneyservice.domain.MemberMoney.Balance;
import com.htwo.moneyservice.domain.MemberMoney.MemberMoneyId;
import com.htwo.moneyservice.domain.MemberMoney.MembershipId;
import org.springframework.stereotype.Component;

@Component
public class MemberMoneyMapper {

  public MemberMoney mapToDomainEntity(MemberMoneyJpaEntity memberMoneyJpaEntity) {
    return MemberMoney.generateMemberMoney(
        new MemberMoneyId(String.valueOf(memberMoneyJpaEntity.getMemberMoneyId())),
        new MembershipId(memberMoneyJpaEntity.getMembershipId()),
        new Balance(memberMoneyJpaEntity.getBalance()),
        new MemberMoney.MoneyAggregateIdentifier(memberMoneyJpaEntity.getAggregateIdentifier())
    );
  }

}
