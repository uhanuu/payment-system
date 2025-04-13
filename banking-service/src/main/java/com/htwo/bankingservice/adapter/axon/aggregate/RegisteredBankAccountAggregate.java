package com.htwo.bankingservice.adapter.axon.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.htwo.bankingservice.adapter.axon.command.CreateRegisteredBankAccountCommand;
import com.htwo.bankingservice.adapter.axon.event.CreateRegisteredBankAccountEvent;
import com.htwo.bankingservice.adapter.out.external.bank.BankAccount;
import com.htwo.bankingservice.adapter.out.external.bank.GetBankAccountRequest;
import com.htwo.bankingservice.application.port.out.RequestBankAccountInfoPort;
import com.htwo.common.saga.command.CheckRegisteredBankAccountCommand;
import com.htwo.common.saga.event.CheckedRegisteredBankAccountEvent;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Slf4j
@Aggregate
@NoArgsConstructor
public class RegisteredBankAccountAggregate {

  @AggregateIdentifier
  private String id;
  private String membershipId;
  private String bankName;
  private String bankAccountNumber;

  @CommandHandler
  public RegisteredBankAccountAggregate(@NotNull CreateRegisteredBankAccountCommand command) {
    log.info("RegisteredBankAccountAggregate command handler");
    apply(new CreateRegisteredBankAccountEvent(
        command.getMembershipId(),
        command.getBankName(),
        command.getBankAccountNumber())
    );
  }

  @CommandHandler
  public void handle(
      @NotNull CheckRegisteredBankAccountCommand command,
      RequestBankAccountInfoPort bankAccountInfoPort
  ) {
    log.info("CheckRegisteredBankAccountCommand command handler with bankAccountInfoPort");
    id = command.getAggregateIdentifier();
    final BankAccount bankAccount = bankAccountInfoPort.getBankAccountInfo(
            new GetBankAccountRequest(command.getBankName(), command.getBankAccountNumber())
        )
        .orElseThrow(RuntimeException::new);
    final String firmBankingUUID = UUID.randomUUID().toString();

    apply(new CheckedRegisteredBankAccountEvent(
        command.getRechargingRequestId(),
        command.getCheckRegisteredBankAccountId(),
        command.getMembershipId(),
        bankAccount.isValid(),
        command.getAmount(),
        firmBankingUUID,
        bankAccount.bankName(),
        bankAccount.bankAccountNumber())
    );
  }

  @EventSourcingHandler
  public void on(CreateRegisteredBankAccountEvent event) {
    log.info("CreateRegisteredBankAccountEvent event sourcing handler");
    id = UUID.randomUUID().toString();
    membershipId = event.getMembershipId();
    bankName = event.getBankName();
    bankAccountNumber = event.getBankAccountNumber();
  }
}
