package com.htwo.membershipservice.adapter.in.web;

import com.htwo.membershipservice.application.port.in.RegisterMembershipCommand;
import com.htwo.membershipservice.application.port.in.RegisterMembershipUseCase;
import com.htwo.common.WebAdapter;
import com.htwo.membershipservice.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@WebAdapter(path = "/api/v1")
@RequiredArgsConstructor
public class RegisterMembershipController {

  private final RegisterMembershipUseCase registerMembershipUseCase;

  @PostMapping(path = "/membership")
  public ResponseEntity<Membership> registerMembership(@RequestBody RegisterMembershipRequest request) {
    RegisterMembershipCommand command = RegisterMembershipCommand.builder()
        .name(request.name())
        .email(request.email())
        .address(request.address())
        .isValid(true)
        .isCorp(request.isCorp())
        .build();

    return ResponseEntity.ok(registerMembershipUseCase.registerMembership(command));
  }

}
