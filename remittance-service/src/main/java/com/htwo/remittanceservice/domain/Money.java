package com.htwo.remittanceservice.domain;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Money {

  // TODO: 현재는 only won 이라서 BigInteger 나중에는 BigDecimal 고민해보기
  private BigInteger moneyAmount;

  public Money(String moneyAmount) {
    this.moneyAmount = validateMoneyAmount(moneyAmount);
  }

  private BigInteger validateMoneyAmount(String moneyAmount) {
    try {
      final BigInteger checkMoneyAmount = new BigInteger(moneyAmount);
      if (checkMoneyAmount.compareTo(BigInteger.ZERO) < 0) {
        throw new IllegalArgumentException("금액은 0 이상이어야 합니다.");
      }
      return checkMoneyAmount;
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("유효한 숫자 형식이 아닙니다.", e);
    }
  }

  private Money(BigInteger moneyAmount) {
    this.moneyAmount = moneyAmount;
  }

  public static Money ZERO() {
    return new Money(BigInteger.ZERO);
  }

  public Money add(Money other) {
    return new Money(this.moneyAmount.add(other.moneyAmount));
  }

  public Money subtract(Money other) {
    return new Money(this.moneyAmount.subtract(other.moneyAmount));
  }

  public Money multiply(int factor) {
    return new Money(this.moneyAmount.multiply(BigInteger.valueOf(factor)));
  }

  public Money divide(int divisor) {
    return new Money(this.moneyAmount.divide(BigInteger.valueOf(divisor)));
  }

  public boolean isGreaterThan(Money other) {
    return this.moneyAmount.compareTo(other.moneyAmount) > 0;
  }

  public boolean isLessThan(Money other) {
    return this.moneyAmount.compareTo(other.moneyAmount) < 0;
  }

  public boolean isEqualTo(Money other) {
    return this.moneyAmount.compareTo(other.moneyAmount) == 0;
  }

  public BigDecimal toBigDecimal() {
    return new BigDecimal(this.moneyAmount);
  }

  /**
   * 10,000원 단위로 올림하는 메서드
   * @return 올림된 Money 객체
   */
  public Money roundUpToNearestTenThousand() {
    BigInteger roundedAmount = toBigDecimal()
        .divide(new BigDecimal("10000"), 0, RoundingMode.CEILING)  // 10000으로 나누고 올림
        .multiply(new BigDecimal("10000")).toBigInteger();

    return new Money(roundedAmount);
  }

  public String toStringValue() {
    return this.moneyAmount.toString();
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Money money = (Money) o;
    return Objects.equals(moneyAmount, money.moneyAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(moneyAmount);
  }
}
