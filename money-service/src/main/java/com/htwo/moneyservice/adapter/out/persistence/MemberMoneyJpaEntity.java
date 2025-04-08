package com.htwo.moneyservice.adapter.out.persistence;

import com.htwo.moneyservice.domain.Money;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Table(name = "member_money")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class MemberMoneyJpaEntity {

  @Id
  @GeneratedValue
  private Long memberMoneyId;
  private String membershipId;
  @Embedded
  private Money balance;
  private String aggregateIdentifier;

  @Builder
  private MemberMoneyJpaEntity(
      String membershipId,
      Money balance,
      String aggregateIdentifier
  ) {
    this.membershipId = membershipId;
    this.balance = balance;
    this.aggregateIdentifier = aggregateIdentifier;
  }

  public void updateBalance(Money balance) {
    this.balance = balance;
  }
  public void incrementBalance(Money incrementBalance) {
    this.balance = balance.add(incrementBalance);
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MemberMoneyJpaEntity that = (MemberMoneyJpaEntity) o;
    return Objects.equals(memberMoneyId, that.memberMoneyId) && Objects.equals(membershipId,
        that.membershipId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(memberMoneyId, membershipId);
  }
}
