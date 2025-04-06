package com.htwo.remittanceservice.adapter.out.web.service.banking;

import com.htwo.common.ExternalSystemAdapter;
import com.htwo.common.web.CommonHttpClient;
import com.htwo.remittanceservice.application.port.out.banking.BankingPort;
import com.htwo.remittanceservice.application.port.out.banking.RemittanceBanking;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankingServiceAdapter implements BankingPort {

  private final CommonHttpClient bankingServiceHttpClient;
  @Value("${service.banking.url}")
  private String bankingServiceEndpoint;

  @Override
  public RemittanceBanking getMembershipBankingInfo(String bankName, String bankAccountNumber) {
    return null;
  }

  @Override
  public boolean requestFirmBanking(String bankName, String bankAccountNumber, String amount) {
    return true;
  }
}
