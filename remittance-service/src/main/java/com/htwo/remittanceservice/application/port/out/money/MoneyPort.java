package com.htwo.remittanceservice.application.port.out.money;

public interface MoneyPort {
  RemittanceMoney getMoneyInfo(String membershipId);
  boolean requestMoneyRecharging(String membershipId, String amount);
  boolean requestMoneyIncrease(String membershipId, String amount);
  boolean requestMoneyDecrease(String membershipId, String amount);
}
