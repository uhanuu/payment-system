package com.htwo.bankingservice.adapter.axon.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.htwo.bankingservice.adapter.axon.command.CreateRequestFirmBankingCommand;
import com.htwo.bankingservice.adapter.axon.event.RequestFirmBankingCreatedEvent;
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

//  @CommandHandler
//  public String handle(@NotNull UpdateRequestFirmBankingCommand command) {
//    // store event
//    apply(new UpdateRequestFirmBankingEvent(command.getAggregateIdentifier(),
//        command.getFirmBankingStatus()));
//    return id;
//  }

//  @EventSourcingHandler
//  public void on(UpdateRequestFirmBankingEvent event) {
//    id = event.getAggregateIdentifier();
//    firmBankingStatus = event.getFirmBankingStatus();
//  }

//  @CommandHandler
//  public RequestFirmBankingAggregate(RequestFirmBankingCommand command,
//                                     RequestFirmBankingPort firmBankingPort,
//                                     RequestExternalFirmBankingPort externalFirmBankingPort) {
//    id = command.getAggregateIdentifier();
//
//    firmBankingPort.createFirmBankingRequest(
//        new FirmBankingRequest.FromBankName(command.getToBankName()),
//        new FirmBankingRequest.FromBankAccountNumber(command.getToBankAccountNumber()),
//        new FirmBankingRequest.ToBankName("fastcampus-bank"),
//        new FirmBankingRequest.ToBankAccountNumber("123-333-9999"),
//        new FirmBankingRequest.MoneyAmount(command.getMoneyAmount()),
//        new FirmBankingRequest.FirmBankingStatus(0),
//        new FirmBankingRequest.FirmBankingAggregateIdentifier(id));
//
//    // firmBanking!
//    FirmBankingResult firmBankingResult = externalFirmBankingPort.requestExternalFirmBanking(
//        new ExternalFirmBankingRequest(
//            command.getFromBankName(),
//            command.getFromBankAccountNumber(),
//            command.getToBankName(),
//            command.getToBankAccountNumber(),
//            command.getMoneyAmount()
//        ));
//
//    // 0. 성공, 1. 실패
//    int resultCode = firmBankingResult.getResultCode();
//
//    apply(new RequestFirmBankingFinishedEvent(
//        command.getRequestFirmBankingId(),
//        command.getRechargeRequestId(),
//        command.getMembershipId(),
//        command.getToBankName(),
//        command.getToBankAccountNumber(),
//        command.getMoneyAmount(),
//        resultCode,
//        id
//    ));
//  }

//  @CommandHandler
//  public RequestFirmBankingAggregate(@NotNull RollbackFirmBankingRequestCommand command,
//                                     RequestFirmBankingPort firmBankingPort,
//                                     RequestExternalFirmBankingPort externalFirmBankingPort) {
//    id = UUID.randomUUID().toString();
//
//    // 법인 계좌 -> 고객 계좌 (롤백)
//    firmBankingPort.createFirmBankingRequest(
//        new FirmBankingRequest.FromBankName("카카오페이"),
//        new FirmBankingRequest.FromBankAccountNumber("123-456-789"),
//        new FirmBankingRequest.ToBankName(command.getBankName()),
//        new FirmBankingRequest.ToBankAccountNumber(command.getBankAccountNumber()),
//        new FirmBankingRequest.MoneyAmount(command.getMoneyAmount()),
//        new FirmBankingRequest.FirmBankingStatus(0),
//        new FirmBankingRequest.FirmBankingAggregateIdentifier(id));
//
//    // firmBanking!
//    externalFirmBankingPort.requestExternalFirmBanking(
//        new ExternalFirmBankingRequest(
//            "카카오페이",
//            "123-456-789",
//            command.getBankName(),
//            command.getBankAccountNumber(),
//            command.getMoneyAmount()
//        ));
//
//    apply(new RollbackFirmBankingFinishedEvent(
//        command.getRollbackFirmBankingId(),
//        command.getMembershipId(),
//        id)
//    );
//  }
}