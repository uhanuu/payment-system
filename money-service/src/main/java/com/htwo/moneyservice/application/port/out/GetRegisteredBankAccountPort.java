package com.htwo.moneyservice.application.port.out;

public interface GetRegisteredBankAccountPort {
  RegisteredBankAccountAggregateIdentifier getRegisteredBankAccount(String membershipId);
}
