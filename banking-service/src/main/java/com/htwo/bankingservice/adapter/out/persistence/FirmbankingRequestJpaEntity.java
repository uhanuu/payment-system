package com.htwo.bankingservice.adapter.out.persistence;

import com.htwo.bankingservice.domain.BankType;
import com.htwo.bankingservice.domain.FirmbankingStatus;
import com.htwo.bankingservice.domain.Money;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "firmbanking_request")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FirmbankingRequestJpaEntity {

  @Id
  @GeneratedValue
  private Long firmBankingRequestId;
  @Enumerated(EnumType.STRING)
  private BankType fromBankType;
  private String fromBankAccountNumber;
  @Enumerated(EnumType.STRING)
  private BankType toBankType;
  private String toBankAccountNumber;
  // Money 객체 안에서 애노테이션으로 인한 JPA 의존성을 주고 싶지 않아서 값 객체로 사용하기 보다는 Convert 사용
  @Convert(converter = MoneyToBigIntegerConvert.class)
  private Money moneyAmount;
  @Enumerated(EnumType.STRING)
  private FirmbankingStatus firmBankingStatus;
  private String firmbankingRequestUuid;
  private String aggregateIdentifier;

  @Builder
  private FirmbankingRequestJpaEntity(
      Long firmBankingRequestId,
      BankType fromBankType,
      String fromBankAccountNumber,
      BankType toBankType,
      String toBankAccountNumber,
      Money moneyAmount,
      FirmbankingStatus firmBankingStatus,
      String firmbankingRequestUuid,
      String aggregateIdentifier
  ) {
    this.firmBankingRequestId = firmBankingRequestId;
    this.fromBankType = fromBankType;
    this.fromBankAccountNumber = fromBankAccountNumber;
    this.toBankType = toBankType;
    this.toBankAccountNumber = toBankAccountNumber;
    this.moneyAmount = moneyAmount;
    this.firmBankingStatus = firmBankingStatus;
    this.firmbankingRequestUuid = firmbankingRequestUuid;
    this.aggregateIdentifier = aggregateIdentifier;
  }

  public void updateFirmBankingStatus(FirmbankingStatus firmBankingStatus) {
    this.firmBankingStatus = firmBankingStatus;
  }
}
