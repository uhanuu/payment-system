package com.htwo.remittanceservice.adapter.out.persistence;

import com.htwo.remittanceservice.domain.Money;
import com.htwo.remittanceservice.domain.RemittanceStatus;
import com.htwo.remittanceservice.domain.RemittanceType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Table(name = "request_remittance")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class RemittanceRequestJpaEntity {

  @Id
  @GeneratedValue
  private Long remittanceRequestId;
  private String fromMembershipId; // from membership
  private String toMembershipId; // to membership
  private String toBankName;
  private String toBankAccountNumber;
  @Enumerated(value = EnumType.STRING)
  private RemittanceType remittanceType;
  @Embedded
  private Money amount;
  @Enumerated(value = EnumType.STRING)
  private RemittanceStatus remittanceStatus;

  @Builder
  private RemittanceRequestJpaEntity(
      Long remittanceRequestId,
      String fromMembershipId,
      String toMembershipId,
      String toBankName,
      String toBankAccountNumber,
      RemittanceType remittanceType,
      Money amount,
      RemittanceStatus remittanceStatus
  ) {
    this.remittanceRequestId = remittanceRequestId;
    this.fromMembershipId = fromMembershipId;
    this.toMembershipId = toMembershipId;
    this.toBankName = toBankName;
    this.toBankAccountNumber = toBankAccountNumber;
    this.remittanceType = remittanceType;
    this.amount = amount;
    this.remittanceStatus = remittanceStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RemittanceRequestJpaEntity that = (RemittanceRequestJpaEntity) o;
    return Objects.equals(remittanceRequestId, that.remittanceRequestId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(remittanceRequestId);
  }
}
