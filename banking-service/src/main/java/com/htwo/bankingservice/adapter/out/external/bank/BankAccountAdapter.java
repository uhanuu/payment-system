package com.htwo.bankingservice.adapter.out.external.bank;

import com.htwo.bankingservice.application.port.out.RequestBankAccountInfoPort;
import com.htwo.bankingservice.application.port.out.RequestExternalFirmbankingPort;
import com.htwo.common.ExternalSystemAdapter;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankAccountAdapter implements RequestBankAccountInfoPort, RequestExternalFirmbankingPort {

  @Override
  public Optional<BankAccount> getBankAccountInfo(GetBankAccountRequest request) {
    // 실제 외부 은행에 API Call 호출 -> 은행 정보 검증 -> 변환
    return Optional.of(new BankAccount(request.bankName(), request.bankAccountNumber(), true));
  }

  @Override
  public FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest request) {
    // 실제 외부 은행에 펌뱅킹 요청 -> 검증 -> 변환
    return new FirmbankingResult("success");
  }
}
