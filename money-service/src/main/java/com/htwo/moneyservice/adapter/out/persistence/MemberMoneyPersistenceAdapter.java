package com.htwo.moneyservice.adapter.out.persistence;

import com.htwo.common.PersistenceAdapter;
import com.htwo.moneyservice.application.port.out.MemberMoneyPort;
import com.htwo.moneyservice.domain.MemberMoney.MembershipId;
import com.htwo.moneyservice.domain.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@Transactional // TODO: Service layer가 맞을까 Persist layer가 맞을까 고민해보자
@RequiredArgsConstructor
public class MemberMoneyPersistenceAdapter implements MemberMoneyPort {

  private final MemberMoneyJpaRepository memberMoneyJpaRepository;

  @Override
  @Transactional // service 단에서 @Transactional이 존재하면 외부 API 트랜잭션 제거하기 힘들어짐...
  public MemberMoneyJpaEntity increaseMemberMoney(
      MembershipId membershipId,
      Money increaseMoneyAmount
  ) {
    // TODO: status 값도 같이 수정해주기
    final MemberMoneyJpaEntity memberMoneyJpaEntity = memberMoneyJpaRepository.findByMembershipId(
        membershipId.membershipId()
    ).orElseGet(() -> createMemberMoney(membershipId.membershipId()));

    final Money balance = memberMoneyJpaEntity.getBalance().add(increaseMoneyAmount);
    memberMoneyJpaEntity.updateBalance(balance);

    return memberMoneyJpaRepository.save(memberMoneyJpaEntity); // 변경감지 안하고 update 될 수 있게 처리
  }

  private MemberMoneyJpaEntity createMemberMoney(String membershipId) {
    return MemberMoneyJpaEntity.builder()
        .membershipId(membershipId)
        .balance(Money.ZERO())
        .build();
  }
}
