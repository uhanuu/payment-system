package com.htwo.membershipservice.adapter.in.web;

import com.htwo.common.WebAdapter;
import com.htwo.membershipservice.application.port.in.ModifyMembershipCommand;
import com.htwo.membershipservice.application.port.in.ModifyMembershipUseCase;
import com.htwo.membershipservice.application.port.in.RegisterMembershipCommand;
import com.htwo.membershipservice.application.port.in.RegisterMembershipUseCase;
import com.htwo.membershipservice.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@WebAdapter(path = "/api/v1")
@RequiredArgsConstructor
public class ModifyMembershipController {

  private final ModifyMembershipUseCase modifyMembershipUseCase;

  @PutMapping(path = "/membership/{membershipId}")
  public ResponseEntity<Membership> registerMembership(
      @PathVariable String membershipId,
      @RequestBody ModifyMembershipRequest request
  ) {
    ModifyMembershipCommand command = ModifyMembershipCommand.builder()
        .membershipId(membershipId)
        .name(request.name())
        .email(request.email())
        .address(request.address())
        .isValid(request.isValid())
        .isCorp(request.isCorp())
        .build();

    return ResponseEntity.ok(modifyMembershipUseCase.modifyMembership(command));
  }

}
