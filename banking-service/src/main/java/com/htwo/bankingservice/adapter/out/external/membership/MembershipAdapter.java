package com.htwo.bankingservice.adapter.out.external.membership;

import com.htwo.bankingservice.application.port.out.RequestValidationMembershipIdPort;
import com.htwo.common.ExternalSystemAdapter;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class MembershipAdapter implements RequestValidationMembershipIdPort {

  @Override
  public Optional<Membership> getMembershipId(GetMembershipByIdRequest request) {
    return Optional.of(
        new Membership(request.membershipId(), "name", "email", "address", true, true)
    );
  }

}
