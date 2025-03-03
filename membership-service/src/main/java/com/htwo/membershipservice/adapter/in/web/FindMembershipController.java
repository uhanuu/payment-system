package com.htwo.membershipservice.adapter.in.web;

import com.htwo.common.WebAdapter;
import com.htwo.membershipservice.application.port.in.FindMembershipCommand;
import com.htwo.membershipservice.application.port.in.FindMembershipUseCase;
import com.htwo.membershipservice.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@WebAdapter(path = "/api/v1")
@RequiredArgsConstructor
public class FindMembershipController {

  private final FindMembershipUseCase findMembershipUseCase;

  @GetMapping(path = "/membership/{membership}")
  public ResponseEntity<Membership> findMembershipById(@PathVariable String membership) {

    FindMembershipCommand command = FindMembershipCommand.builder()
        .membershipId(membership)
        .build();

    return ResponseEntity.ok(findMembershipUseCase.findMembership(command));
  }

}
