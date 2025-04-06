package com.htwo.remittanceservice.adapter.out.web.service.membership;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.htwo.common.ExternalSystemAdapter;
import com.htwo.common.web.CommonHttpClient;
import com.htwo.remittanceservice.application.port.out.membership.MembershipPort;
import com.htwo.remittanceservice.application.port.out.membership.MembershipStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class MembershipServiceAdapter implements MembershipPort {

  private final CommonHttpClient membershipServiceHttpClient;

  @Value("${service.membership.url}")
  private String membershipServiceEndpoint;

  @Override
  public MembershipStatus getMembershipStatus(String membershipId) {

    String buildUrl = String.join("/", this.membershipServiceEndpoint, "membership", membershipId);
    try {
      String jsonResponse = membershipServiceHttpClient.sendGetRequest(buildUrl).body();
      ObjectMapper mapper = new ObjectMapper();

      Membership findMembership = mapper.readValue(jsonResponse, Membership.class);
      if (findMembership.valid()){
        return new MembershipStatus(findMembership.membershipId(), true);
      } else{
        return new MembershipStatus(findMembership.membershipId(), false);
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
