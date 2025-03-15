package com.htwo.bankingservice.application.port.out;

import com.htwo.bankingservice.adapter.out.external.membership.GetMembershipByIdRequest;
import com.htwo.bankingservice.adapter.out.external.membership.Membership;
import java.util.Optional;

public interface RequestValidationMembershipIdPort {
  Optional<Membership> getMembershipId(GetMembershipByIdRequest request);
}
