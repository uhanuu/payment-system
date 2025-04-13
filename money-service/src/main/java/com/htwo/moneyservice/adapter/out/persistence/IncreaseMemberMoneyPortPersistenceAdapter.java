package com.htwo.moneyservice.adapter.out.persistence;

import com.htwo.common.PersistenceAdapter;
import com.htwo.moneyservice.application.port.out.CreateMemberMoneyPort;
import com.htwo.moneyservice.application.port.out.IncreaseMemberMoneyPort;
import com.htwo.moneyservice.domain.MemberMoney.MembershipId;
import com.htwo.moneyservice.domain.MemberMoney.MoneyAggregateIdentifier;
import com.htwo.moneyservice.domain.Money;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@Transactional // TODO: Service layer가 맞을까 Persist layer가 맞을까 고민해보자
@RequiredArgsConstructor
public class IncreaseMemberMoneyPortPersistenceAdapter implements IncreaseMemberMoneyPort, CreateMemberMoneyPort {

  private final MemberMoneyJpaRepository memberMoneyJpaRepository;

  @Override
  @Transactional // service 단에서 @Transactional이 존재하면 외부 API 트랜잭션 제거하기 힘들어짐...
  public MemberMoneyJpaEntity increaseMemberMoney(
      MembershipId membershipId,
      Money increaseMoneyAmount
  ) {
    final MemberMoneyJpaEntity memberMoneyJpaEntity = memberMoneyJpaRepository.findByMembershipId(
        membershipId.membershipId()
    ).orElseGet(() -> createMemberMoney(
            membershipId.membershipId(),
            UUID.randomUUID().toString()
        )
    );

    memberMoneyJpaEntity.incrementBalance(increaseMoneyAmount);
    return memberMoneyJpaRepository.save(memberMoneyJpaEntity); // 변경감지 안하고 update 될 수 있게 처리
  }

  @Override
  public void createMemberMoney(
      MembershipId membershipId,
      MoneyAggregateIdentifier aggregateIdentifier
  ) {
    createMemberMoney(membershipId.membershipId(), aggregateIdentifier.moneyAggregateIdentifier());
  }

  private MemberMoneyJpaEntity createMemberMoney(
      String membershipId,
      String aggregateIdentifier
  ) {
    MemberMoneyJpaEntity entity = MemberMoneyJpaEntity.builder()
        .membershipId(membershipId)
        .balance(Money.ZERO())
        .aggregateIdentifier(aggregateIdentifier)
        .build();

    return memberMoneyJpaRepository.save(entity);
  }
}
