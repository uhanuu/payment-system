package com.htwo.bankingservice.adapter.in.web;

import com.htwo.bankingservice.application.port.in.FindRegisteredBankAccountQuery;
import com.htwo.bankingservice.application.port.in.FindRegisteredBankAccountUseCase;
import com.htwo.bankingservice.domain.RegisteredBankAccount;
import com.htwo.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@WebAdapter(path = "/api/v1")
@RequiredArgsConstructor
public class FindRegisteredBankAccountController {

  private final FindRegisteredBankAccountUseCase findRegisteredBankAccountUseCase;

  @PostMapping(path = "/banking/account/{registeredBankAccountId}")
  public ResponseEntity<RegisteredBankAccount> findRegisteredBankAccount(
      @PathVariable String registeredBankAccountId,
      @RequestParam String membershipId
  ) {
    FindRegisteredBankAccountQuery query = new FindRegisteredBankAccountQuery(
        registeredBankAccountId, membershipId
    );

    return ResponseEntity.ok(findRegisteredBankAccountUseCase.findRegisteredBankAccount(query));
  }
}
