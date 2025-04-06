package com.htwo.remittanceservice.application.port.out.banking;

public interface BankingPort {
  RemittanceBanking getMembershipBankingInfo(String bankName, String bankAccountNumber);
  boolean requestFirmBanking(String bankName, String bankAccountNumber, String amount);
}
