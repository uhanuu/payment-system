package com.htwo.remittanceservice.application.port.in;

import com.htwo.remittanceservice.domain.RemittanceRequest;

public interface RequestRemittanceUseCase {
  RemittanceRequest requestRemittance(RequestRemittanceCommand command);
}
