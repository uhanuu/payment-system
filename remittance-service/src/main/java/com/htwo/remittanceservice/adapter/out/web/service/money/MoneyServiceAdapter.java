package com.htwo.remittanceservice.adapter.out.web.service.money;

import com.htwo.common.ExternalSystemAdapter;
import com.htwo.common.web.CommonHttpClient;
import com.htwo.remittanceservice.application.port.out.money.MoneyPort;
import com.htwo.remittanceservice.application.port.out.money.RemittanceMoney;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class MoneyServiceAdapter implements MoneyPort {
  private final CommonHttpClient moneyServiceHttpClient;
  @Value("${service.money.url}")
  private String moneyServiceEndpoint;

  @Override
  public RemittanceMoney getMoneyInfo(String membershipId) {
    return new RemittanceMoney(
        "1", "100000"
    );
  }

  @Override
  public boolean requestMoneyRecharging(String membershipId, String amount) {
    return true;
  }

  @Override
  public boolean requestMoneyIncrease(String membershipId, String amount) {
    return true;
  }

  @Override
  public boolean requestMoneyDecrease(String membershipId, String amount) {
    return true;
  }
}
