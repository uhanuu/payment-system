package com.htwo.bankingservice.adapter.axon.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.htwo.bankingservice.adapter.axon.command.CreateRequestFirmBankingCommand;
import com.htwo.bankingservice.adapter.axon.command.UpdateRequestFirmBankingCommand;
import com.htwo.bankingservice.adapter.axon.event.RequestFirmBankingCreatedEvent;
import com.htwo.bankingservice.adapter.axon.event.UpdateRequestFirmBankingEvent;
import com.htwo.bankingservice.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.htwo.bankingservice.adapter.out.external.bank.FirmbankingResult;
import com.htwo.bankingservice.application.port.out.RequestExternalFirmbankingPort;
import com.htwo.bankingservice.application.port.out.RequestFirmbankingPort;
import com.htwo.bankingservice.domain.BankType;
import com.htwo.bankingservice.domain.FirmbankingRequest;
import com.htwo.bankingservice.domain.FirmbankingRequest.DomainFirmBankingStatus;
import com.htwo.bankingservice.domain.FirmbankingRequest.FirmbankingRequestUuid;
import com.htwo.bankingservice.domain.FirmbankingRequest.FromBankAccountNumber;
import com.htwo.bankingservice.domain.FirmbankingRequest.FromBankName;
import com.htwo.bankingservice.domain.FirmbankingRequest.MoneyAmount;
import com.htwo.bankingservice.domain.FirmbankingRequest.ToBankAccountNumber;
import com.htwo.bankingservice.domain.FirmbankingRequest.ToBankName;
import com.htwo.bankingservice.domain.FirmbankingStatus;
import com.htwo.common.saga.command.RequestFirmBankingCommand;
import com.htwo.common.saga.command.RollbackFirmBankingRequestCommand;
import com.htwo.common.saga.event.RequestFirmBankingFinishedEvent;
import com.htwo.common.saga.event.RollbackFirmBankingFinishedEvent;
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
    apply(new RequestFirmBankingCreatedEvent(
        command.getFromBankType(),
        command.getFromBankAccountNumber(),
        command.getToBankType(),
        command.getToBankAccountNumber(),
        command.getMoneyAmount()));
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

  @CommandHandler
  public RequestFirmBankingAggregate(
      RequestFirmBankingCommand command,
      RequestFirmbankingPort firmBankingPort,
      RequestExternalFirmbankingPort externalFirmBankingPort
  ) {
    id = command.getAggregateIdentifier();

    // from -> to
    firmBankingPort.createRequestFirmbanking(
        new FromBankName(command.getToBankName()),
        new FromBankAccountNumber(command.getToBankAccountNumber()),
        new ToBankName(command.getToBankName()),
        new ToBankAccountNumber(command.getToBankAccountNumber()),
        new MoneyAmount(String.valueOf(command.getMoneyAmount())),
        new DomainFirmBankingStatus(FirmbankingStatus.REQUESTED),
        new FirmbankingRequestUuid(command.getRequestFirmBankingId()),
        new FirmbankingRequest.AggregateIdentifier(id));

    // firmBanking!
    FirmbankingResult firmBankingResult = externalFirmBankingPort.requestExternalFirmbanking(
        ExternalFirmbankingRequest.builder()
            .fromBankName(command.getFromBankName())
            .fromBankAccountNumber(command.getFromBankAccountNumber())
            .toBankName(command.getToBankName())
            .toBankAccountNumber(command.getToBankAccountNumber())
            .moneyAmount(String.valueOf(command.getMoneyAmount()))
            .build()
    );

    String resultCode = firmBankingResult.resultCode();

    apply(new RequestFirmBankingFinishedEvent(
        command.getRequestFirmBankingId(),
        command.getRechargeRequestId(),
        command.getMembershipId(),
        command.getToBankName(),
        command.getToBankAccountNumber(),
        String.valueOf(command.getMoneyAmount()),
        resultCode,
        id
    ));
  }

  @CommandHandler
  public RequestFirmBankingAggregate(
      @NotNull RollbackFirmBankingRequestCommand command,
      RequestFirmbankingPort firmBankingPort,
      RequestExternalFirmbankingPort externalFirmBankingPort
  ) {
    id = UUID.randomUUID().toString();

    // 법인 계좌 -> 고객 계좌 (롤백)
    firmBankingPort.createRequestFirmbanking(
        new FromBankName("카카오뱅크"),
        new FromBankAccountNumber("123-456-789"),
        new ToBankName(command.getBankName()),
        new ToBankAccountNumber(command.getBankAccountNumber()),
        new MoneyAmount(String.valueOf(command.getMoneyAmount())),
        new DomainFirmBankingStatus(FirmbankingStatus.REQUESTED),
        new FirmbankingRequestUuid(command.getRollbackFirmBankingId()),
        new FirmbankingRequest.AggregateIdentifier(id));

    // firmBanking!
    externalFirmBankingPort.requestExternalFirmbanking(
        ExternalFirmbankingRequest.builder()
            .fromBankName("카카오뱅크")
            .fromBankAccountNumber("123-456-789")
            .toBankName(command.getBankName())
            .toBankAccountNumber(command.getBankAccountNumber())
            .moneyAmount(String.valueOf(command.getMoneyAmount()))
            .build()
    );

    apply(new RollbackFirmBankingFinishedEvent(
        command.getRollbackFirmBankingId(),
        command.getMembershipId(),
        id)
    );
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

  @EventSourcingHandler
  public void on(UpdateRequestFirmBankingEvent event) {
    log.info("UpdateRequestFirmBankingEvent event handler");
    id = event.aggregateIdentifier();
    firmBankingStatus = event.firmBankingStatus();
  }
}