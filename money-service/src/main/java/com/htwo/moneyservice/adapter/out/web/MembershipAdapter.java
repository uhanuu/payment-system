package com.htwo.moneyservice.adapter.out.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.htwo.common.ExternalWebAdapter;
import com.htwo.common.web.CommonHttpClient;
import com.htwo.moneyservice.application.port.out.GetMembershipPort;
import com.htwo.moneyservice.domain.MembershipStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@ExternalWebAdapter
@RequiredArgsConstructor
public class MembershipAdapter implements GetMembershipPort {

  private final CommonHttpClient commonHttpClient;
  private final ObjectMapper mapper;
  @Value("${service.membership.url}")
  private String membershipServiceUrl;

  @Override
  public MembershipStatus getMembership(String membershipId) {
    String url = String.join("/", membershipServiceUrl, "membership", membershipId);

    try {
      String jsonResponse = commonHttpClient.sendGetRequest(url).body();
      Membership membership = mapper.readValue(jsonResponse, Membership.class);

      return new MembershipStatus(membership.membershipId(), membership.valid());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
