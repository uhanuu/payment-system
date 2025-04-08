package com.htwo.moneyservice.adapter.out.persistence;

import com.htwo.common.PersistenceAdapter;
import com.htwo.moneyservice.application.port.out.GetMemberMoneyPort;
import com.htwo.moneyservice.domain.MemberMoney;
import com.htwo.moneyservice.domain.MemberMoney.MembershipId;
import com.htwo.moneyservice.domain.Money;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class GetMemberMoneyAdapter implements GetMemberMoneyPort {

  private final MemberMoneyJpaRepository memberMoneyJpaRepository;
  private final MemberMoneyMapper memberMoneyMapper;

  @Override
  public MemberMoney getMemberMoney(MembershipId membershipId) {
    final MemberMoneyJpaEntity memberMoneyJpaEntity = memberMoneyJpaRepository.findByMembershipId(
        membershipId.membershipId()
    ).orElseGet(() -> createMemberMoney(membershipId.membershipId()));

    return memberMoneyMapper.mapToDomainEntity(memberMoneyJpaEntity);
  }

  private MemberMoneyJpaEntity createMemberMoney(String membershipId) {
    final UUID aggregateIdentifier = UUID.randomUUID();

    return MemberMoneyJpaEntity.builder()
        .membershipId(membershipId)
        .balance(Money.ZERO())
        .aggregateIdentifier(aggregateIdentifier.toString())
        .build();
  }
}
