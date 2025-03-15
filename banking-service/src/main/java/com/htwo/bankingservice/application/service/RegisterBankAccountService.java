package com.htwo.bankingservice.application.service;


import com.htwo.bankingservice.adapter.out.external.bank.BankAccount;
import com.htwo.bankingservice.adapter.out.external.bank.GetBankAccountRequest;
import com.htwo.bankingservice.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.htwo.bankingservice.adapter.out.persistence.RegisteredBankAccountMapper;
import com.htwo.bankingservice.application.port.in.RegisterBankAccountCommand;
import com.htwo.bankingservice.application.port.in.RegisterBankAccountUseCase;
import com.htwo.bankingservice.application.port.out.RegisterBankAccountPort;
import com.htwo.bankingservice.application.port.out.RequestBankAccountInfoPort;
import com.htwo.bankingservice.domain.RegisteredBankAccount;
import com.htwo.bankingservice.domain.RegisteredBankAccount.BankAccountNumber;
import com.htwo.bankingservice.domain.RegisteredBankAccount.LinkedStatusIsValid;
import com.htwo.bankingservice.domain.RegisteredBankAccount.MembershipId;
import com.htwo.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

  private final RegisterBankAccountPort registerBankAccountPort;
  private final RequestBankAccountInfoPort requestBankAccountInfoPort;
  private final RegisteredBankAccountMapper registeredBankAccountMapper;

  @Override
  public RegisteredBankAccount registerMembership(RegisterBankAccountCommand command) {
    final GetBankAccountRequest bankAccountRequest = new GetBankAccountRequest(command.getBankType(), command.getBankAccountNumber());
    String membershipId = command.getMembershipId();

    final BankAccount bankAccountInfo = requestBankAccountInfoPort.getBankAccountInfo(bankAccountRequest)
        .orElseThrow(RuntimeException::new);

    if (bankAccountInfo.isValid()) {
      return createRegisteredBankAccount(command);
    }
    return null;
  }

  private RegisteredBankAccount createRegisteredBankAccount(RegisterBankAccountCommand command) {
    final RegisteredBankAccountJpaEntity registeredBankAccount = registerBankAccountPort.createRegisteredBankAccount(
        new MembershipId(command.getMembershipId()),
        new BankAccountNumber(command.getBankAccountNumber()),
        command.getBankType(),
        new LinkedStatusIsValid(command.isLinkedStatusIsValid())
    );

    return registeredBankAccountMapper.mapToDomainEntity(registeredBankAccount);
  }
}
