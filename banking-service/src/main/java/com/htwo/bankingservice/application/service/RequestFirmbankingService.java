package com.htwo.bankingservice.application.service;


import com.htwo.bankingservice.adapter.axon.command.CreateRequestFirmBankingCommand;
import com.htwo.bankingservice.adapter.axon.command.UpdateRequestFirmBankingCommand;
import com.htwo.bankingservice.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.htwo.bankingservice.adapter.out.external.bank.FirmbankingResult;
import com.htwo.bankingservice.application.port.in.RequestFirmbankingCommand;
import com.htwo.bankingservice.application.port.in.RequestFirmbankingUseCase;
import com.htwo.bankingservice.application.port.in.UpdateFirmBankingCommand;
import com.htwo.bankingservice.application.port.in.UpdateFirmBankingUseCase;
import com.htwo.bankingservice.application.port.out.FindRequestFirmbankingPort;
import com.htwo.bankingservice.application.port.out.RequestExternalFirmbankingPort;
import com.htwo.bankingservice.application.port.out.RequestFirmbankingPort;
import com.htwo.bankingservice.domain.BankType;
import com.htwo.bankingservice.domain.FirmbankingRequest;
import com.htwo.bankingservice.domain.FirmbankingRequest.AggregateIdentifier;
import com.htwo.bankingservice.domain.FirmbankingRequest.DomainFirmBankingStatus;
import com.htwo.bankingservice.domain.FirmbankingRequest.FirmbankingRequestUuid;
import com.htwo.bankingservice.domain.FirmbankingRequest.FromBankAccountNumber;
import com.htwo.bankingservice.domain.FirmbankingRequest.FromBankName;
import com.htwo.bankingservice.domain.FirmbankingRequest.MoneyAmount;
import com.htwo.bankingservice.domain.FirmbankingRequest.ToBankAccountNumber;
import com.htwo.bankingservice.domain.FirmbankingRequest.ToBankName;
import com.htwo.bankingservice.domain.FirmbankingStatus;
import com.htwo.bankingservice.domain.Money;
import com.htwo.common.UseCase;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class RequestFirmbankingService implements RequestFirmbankingUseCase, UpdateFirmBankingUseCase {

  private final RequestFirmbankingPort requestFirmbankingPort;
  private final RequestExternalFirmbankingPort requestExternalFirmbankingPort;
  private final FindRequestFirmbankingPort findRequestFirmbankingPort;
  private final CommandGateway commandGateway;

  @Override
  public void requestFirmBanking(RequestFirmbankingCommand command) {
    Money money = new Money(command.getMoneyAmount());
    CreateRequestFirmBankingCommand createRequestFirmBankingCommand = CreateRequestFirmBankingCommand.builder()
        .fromBankType(BankType.getBankType(command.getFromBankName()))
        .fromBankAccountNumber(command.getFromBankAccountNumber())
        .toBankType(BankType.getBankType(command.getToBankName()))
        .toBankAccountNumber(command.getToBankAccountNumber())
        .moneyAmount(money.toStringValue())
        .build();

    commandGateway.send(createRequestFirmBankingCommand)
        .whenComplete(
            (result, throwable) -> {
              if (throwable != null) {
                log.error("throwable={}", throwable.getMessage());
                throw new RuntimeException(throwable);
              }
              final UUID firmbankingRequestUuid = UUID.randomUUID();
              final FirmbankingRequest firmbankingRequest = saveRequestFormBanking(
                  command,
                  firmbankingRequestUuid.toString(),
                  result.toString()
              );

              // TODO: 외부 API CALL 트랜잭션 분리
              log.info("[{}] API call external firmbanking request", firmbankingRequestUuid);
              FirmbankingResult firmbankingResult = requestExternalFirmbanking(command);

              // TODO: 하드 코딩 제거
              if ("success".equals(firmbankingResult.resultCode())) {
                log.info("[{}] completed external firmbanking", firmbankingRequestUuid);
                requestFirmbankingPort.modifyRequestFirmbankingStatus(
                    firmbankingRequest,
                    FirmbankingStatus.COMPLETED
                );
              } else {
                log.error("[{}] failed external firmbanking", firmbankingRequestUuid);
                requestFirmbankingPort.modifyRequestFirmbankingStatus(
                    firmbankingRequest,
                    FirmbankingStatus.FAILED
                );
              }
            }
        );
  }

  private FirmbankingRequest saveRequestFormBanking(
      RequestFirmbankingCommand command,
      String firmbankingRequestUuid,
      String aggregateIdentifier
  ) {

    return requestFirmbankingPort.createRequestFirmbanking(
        new FromBankName(command.getFromBankName()),
        new FromBankAccountNumber(command.getFromBankAccountNumber()),
        new ToBankName(command.getToBankName()),
        new ToBankAccountNumber(command.getToBankAccountNumber()),
        new MoneyAmount(command.getMoneyAmount()),
        new DomainFirmBankingStatus(FirmbankingStatus.REQUESTED),
        new FirmbankingRequestUuid(firmbankingRequestUuid),
        new AggregateIdentifier(aggregateIdentifier)
    );
  }

  private FirmbankingResult requestExternalFirmbanking(RequestFirmbankingCommand command) {
    final ExternalFirmbankingRequest request = ExternalFirmbankingRequest.builder()
        .fromBankName(command.getFromBankName())
        .fromBankAccountNumber(command.getFromBankAccountNumber())
        .toBankName(command.getToBankName())
        .toBankAccountNumber(command.getToBankAccountNumber())
        .moneyAmount(command.getMoneyAmount())
        .build();

    return requestExternalFirmbankingPort.requestExternalFirmbanking(request);
  }

  @Override
  public void updateFirmBanking(UpdateFirmBankingCommand command) {
    final FirmbankingStatus firmbankingStatus = FirmbankingStatus.getFirmbankingStatus(command.getFirmBankingStatus());
    commandGateway.send(new UpdateRequestFirmBankingCommand(
        command.getFirmBankingAggregateIdentifier(),
        firmbankingStatus
    )).whenComplete(
        (result, throwable) -> {
          if (throwable != null) {
            throw new RuntimeException(throwable);
          }
          FirmbankingRequest firmBankingRequest = findRequestFirmbankingPort.getFirmBankingRequest(
              new AggregateIdentifier(result.toString()));

          if (firmBankingRequest.getFirmBankingStatus().isFailed()) {
            throw new RuntimeException(throwable);
          }
          requestFirmbankingPort.modifyRequestFirmbankingStatus(firmBankingRequest, firmbankingStatus);
        }
    );

  }
}
