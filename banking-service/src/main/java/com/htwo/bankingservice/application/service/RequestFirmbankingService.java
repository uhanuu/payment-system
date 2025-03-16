package com.htwo.bankingservice.application.service;


import com.htwo.bankingservice.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.htwo.bankingservice.adapter.out.external.bank.FirmbankingResult;
import com.htwo.bankingservice.adapter.out.persistence.FirmbankingRequestJpaEntity;
import com.htwo.bankingservice.adapter.out.persistence.RequestFirmbankingMapper;
import com.htwo.bankingservice.application.port.in.RequestFirmbankingCommand;
import com.htwo.bankingservice.application.port.in.RequestFirmbankingUseCase;
import com.htwo.bankingservice.application.port.out.RequestExternalFirmbankingPort;
import com.htwo.bankingservice.application.port.out.RequestFirmbankingPort;
import com.htwo.bankingservice.domain.FirmbankingRequest;
import com.htwo.bankingservice.domain.FirmbankingRequest.DomainFirmBankingStatus;
import com.htwo.bankingservice.domain.FirmbankingRequest.FromBankAccountNumber;
import com.htwo.bankingservice.domain.FirmbankingRequest.FromBankType;
import com.htwo.bankingservice.domain.FirmbankingRequest.MoneyAmount;
import com.htwo.bankingservice.domain.FirmbankingRequest.ToBankAccountNumber;
import com.htwo.bankingservice.domain.FirmbankingRequest.ToBankType;
import com.htwo.bankingservice.domain.FirmbankingStatus;
import com.htwo.bankingservice.domain.Money;
import com.htwo.common.UseCase;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class RequestFirmbankingService implements RequestFirmbankingUseCase {

  private final RequestFirmbankingPort requestFirmbankingPort;
  private final RequestExternalFirmbankingPort requestExternalFirmbankingPort;
  private final RequestFirmbankingMapper mapper;

  @Override
  public FirmbankingRequest requestFirmBanking(RequestFirmbankingCommand command) {
    final UUID firmbankingRequestUuid = UUID.randomUUID();
    final FirmbankingRequestJpaEntity saveRequestFirmbankingJpaEntity = saveRequestFormBanking(
        command, firmbankingRequestUuid
    );

    // TODO: 외부 API CALL 트랜잭션 분리
    log.info("[{}] API call external firmbanking request", firmbankingRequestUuid);
    FirmbankingResult firmbankingResult = requestExternalFirmbanking(command);
    // TODO: 하드 코딩 제거
    if ("success".equals(firmbankingResult.resultCode())) {
      log.info("[{}] completed external firmbanking", firmbankingRequestUuid);
      saveRequestFirmbankingJpaEntity.updateFirmBankingStatus(FirmbankingStatus.COMPLETED);
    } else {
      log.error("[{}] failed external firmbanking", firmbankingRequestUuid);
      saveRequestFirmbankingJpaEntity.updateFirmBankingStatus(FirmbankingStatus.FAILED);
    }

    final FirmbankingRequestJpaEntity modifyRequestFirmbankingJpaEntity = requestFirmbankingPort.modifyRequestFirmbankingStatus(
        saveRequestFirmbankingJpaEntity
    );
    return mapper.mapToDomainEntity(modifyRequestFirmbankingJpaEntity);
  }

  private FirmbankingRequestJpaEntity saveRequestFormBanking(
      RequestFirmbankingCommand command,
      UUID firmbankingRequestUuid
  ) {
    final Money moneyAmount = new Money(command.getMoneyAmount());

    return requestFirmbankingPort.createRequestFirmbanking(
        new FromBankType(command.getFromBankType()),
        new FromBankAccountNumber(command.getFromBankAccountNumber()),
        new ToBankType(command.getToBankType()),
        new ToBankAccountNumber(command.getToBankAccountNumber()),
        new MoneyAmount(moneyAmount),
        new DomainFirmBankingStatus(FirmbankingStatus.REQUEST),
        firmbankingRequestUuid
    );
  }

  private FirmbankingResult requestExternalFirmbanking(RequestFirmbankingCommand command) {
    final ExternalFirmbankingRequest request = ExternalFirmbankingRequest.builder()
        .fromBankName(command.getFromBankType().getName())
        .fromBankAccountNumber(command.getFromBankAccountNumber())
        .toBankName(command.getToBankType().getName())
        .toBankAccountNumber(command.getToBankAccountNumber())
        .moneyAmount(command.getMoneyAmount())
        .build();

    return requestExternalFirmbankingPort.requestExternalFirmbanking(request);
  }
}
