package com.htwo.moneyservice.application.port.in;

import com.htwo.moneyservice.domain.MoneyChangingRequest;

public interface IncreaseMoneyRequestUseCase {
  MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command);
}
