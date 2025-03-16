package com.htwo.moneyservice.adapter.out.persistence;

import com.htwo.moneyservice.domain.ChangingMoneyStatus;
import com.htwo.moneyservice.domain.ChangingMoneyType;
import com.htwo.moneyservice.domain.Money;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Table(name = "money_changing_request")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class MoneyChangingRequestJpaEntity {

  @Id
  @GeneratedValue
  private Long moneyChangingRequestId;
  private String targetMembershipId;
  @Enumerated(EnumType.STRING)
  private ChangingMoneyType changingMoneyType;
  @Enumerated(EnumType.STRING)
  private ChangingMoneyStatus changingMoneyStatus;
  @Embedded
  private Money moneyAmount;
  private String moneyChangingRequestUuid;
  @CreatedDate
  private LocalDateTime createdAt;

  @Builder
  private MoneyChangingRequestJpaEntity(
      String targetMembershipId,
      ChangingMoneyType changingMoneyType,
      ChangingMoneyStatus changingMoneyStatus,
      Money moneyAmount,
      String moneyChangingRequestUuid
  ) {
    this.targetMembershipId = targetMembershipId;
    this.changingMoneyType = changingMoneyType;
    this.changingMoneyStatus = changingMoneyStatus;
    this.moneyAmount = moneyAmount;
    this.moneyChangingRequestUuid = moneyChangingRequestUuid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MoneyChangingRequestJpaEntity that = (MoneyChangingRequestJpaEntity) o;
    return Objects.equals(moneyChangingRequestId, that.moneyChangingRequestId) && Objects.equals(
        targetMembershipId, that.targetMembershipId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(moneyChangingRequestId, targetMembershipId);
  }
}
