package com.htwo.bankingservice.adapter.axon.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.htwo.bankingservice.adapter.axon.command.CreateRequestFirmBankingCommand;
import com.htwo.bankingservice.adapter.axon.command.UpdateRequestFirmBankingCommand;
import com.htwo.bankingservice.adapter.axon.event.RequestFirmBankingCreatedEvent;
import com.htwo.bankingservice.adapter.axon.event.UpdateRequestFirmBankingEvent;
import com.htwo.bankingservice.domain.BankType;
import com.htwo.bankingservice.domain.FirmbankingStatus;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Slf4j
@Aggregate
@Data
@NoArgsConstructor
public class RequestFirmBankingAggregate {

  @AggregateIdentifier
  private String id;
  private BankType fromBankType;
  private String fromBankAccountNumber;
  private BankType toBankType;
  private String toBankAccountNumber;

  private String moneyAmount;
  private FirmbankingStatus firmBankingStatus;

  @CommandHandler
  public RequestFirmBankingAggregate(@NotNull CreateRequestFirmBankingCommand command) {
    log.info("CreateRequestFirmBankingCommand command handler");
    apply(new RequestFirmBankingCreatedEvent(command.getFromBankType(),
        command.getFromBankAccountNumber(),
        command.getToBankType(),
        command.getToBankAccountNumber(),
        command.getMoneyAmount()));
  }

  @EventSourcingHandler
  public void on(RequestFirmBankingCreatedEvent event) {
    log.info("RequestFirmBankingCreatedEvent event sourcing handler");
    id = UUID.randomUUID().toString();
    fromBankType = event.getFromBankType();
    fromBankAccountNumber = event.getFromBankAccountNumber();
    toBankType = event.getToBankType();
    toBankAccountNumber = event.getToBankAccountNumber();
    moneyAmount = event.getMoneyAmount();
  }

  @CommandHandler
  public String handle(@NotNull UpdateRequestFirmBankingCommand command) {
    log.info("UpdateRequestFirmBankingCommand command handler");
    this.id = command.getAggregateIdentifier();
    apply(new UpdateRequestFirmBankingEvent(
        command.getAggregateIdentifier(),
        command.getFirmBankingStatus())
    );

    return id;
  }

  @EventSourcingHandler
  public void on(UpdateRequestFirmBankingEvent event) {
    log.info("UpdateRequestFirmBankingEvent event handler");
    id = event.aggregateIdentifier();
    firmBankingStatus = event.firmBankingStatus();
  }
}