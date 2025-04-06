package com.htwo.remittanceservice.application.port.out;

import com.htwo.remittanceservice.application.port.in.RequestRemittanceCommand;
import com.htwo.remittanceservice.domain.RemittanceRequest;

public interface RequestRemittancePort {
  RemittanceRequest createRemittanceRequestHistory(RequestRemittanceCommand command);
  boolean saveRemittanceRequestHistory(RemittanceRequest remittanceRequest);
}
