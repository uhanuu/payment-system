package com.htwo.bankingservice.adapter.out.persistence;

import com.htwo.bankingservice.domain.Money;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.math.BigInteger;

@Converter
// @Converter(autoApply = true) 글로벌 설정보다는 JPA Entity에서 확인하는게 좋아서..ㅎ
public class MoneyToBigIntegerConvert implements AttributeConverter<Money, BigInteger> {

  @Override
  public BigInteger convertToDatabaseColumn(Money money) {
    return money.getMoneyAmount();
  }

  @Override
  public Money convertToEntityAttribute(BigInteger bigInteger) {
    return new Money(bigInteger);
  }
}
