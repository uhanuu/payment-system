package com.htwo.bankingservice.application.service;


import com.htwo.bankingservice.adapter.out.external.bank.BankAccount;
import com.htwo.bankingservice.adapter.out.external.bank.GetBankAccountRequest;
import com.htwo.bankingservice.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.htwo.bankingservice.adapter.out.persistence.RegisteredBankAccountMapper;
import com.htwo.bankingservice.application.port.in.RegisterBankAccountCommand;
import com.htwo.bankingservice.application.port.in.RegisterBankAccountUseCase;
import com.htwo.bankingservice.application.port.out.GetMembershipPort;
import com.htwo.bankingservice.application.port.out.MembershipStatus;
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

  private final GetMembershipPort getMembershipPort;
  private final RegisterBankAccountPort registerBankAccountPort;
  private final RequestBankAccountInfoPort requestBankAccountInfoPort;
  private final RegisteredBankAccountMapper registeredBankAccountMapper;

  @Override
  public RegisteredBankAccount registerMembership(RegisterBankAccountCommand command) {
    final GetBankAccountRequest bankAccountRequest = new GetBankAccountRequest(command.getBankType().getName(), command.getBankAccountNumber());
    // TODO: 도메인 지식이 생기면 어떤걸 검증하는지 명확한 메서드 명으로 변경
    MembershipStatus membershipStatus = getMembershipPort.getMembership(command.getMembershipId());
    validationMembership(membershipStatus);
    validationBankAccount(bankAccountRequest);

    return createRegisteredBankAccount(command);
  }

  private void validationMembership(MembershipStatus membershipStatus) {
    if (!membershipStatus.isValid()) {
      throw new IllegalStateException();
    }
  }

  private void validationBankAccount(GetBankAccountRequest bankAccountRequest) {
    final BankAccount bankAccountInfo = requestBankAccountInfoPort.getBankAccountInfo(bankAccountRequest)
        .orElseThrow(RuntimeException::new);

    if (!bankAccountInfo.isValid()) {
      throw new RuntimeException();
    }
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
