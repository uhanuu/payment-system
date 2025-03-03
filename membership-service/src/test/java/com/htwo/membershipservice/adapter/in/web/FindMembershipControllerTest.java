package com.htwo.membershipservice.adapter.in.web;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.htwo.membershipservice.adapter.out.persistence.MembershipJpaEntity;
import com.htwo.membershipservice.adapter.out.persistence.MembershipJpaRepository;
import com.htwo.membershipservice.domain.Membership;
import com.htwo.membershipservice.domain.Membership.MembershipAddress;
import com.htwo.membershipservice.domain.Membership.MembershipEmail;
import com.htwo.membershipservice.domain.Membership.MembershipId;
import com.htwo.membershipservice.domain.Membership.MembershipIsCorp;
import com.htwo.membershipservice.domain.Membership.MembershipIsValid;
import com.htwo.membershipservice.domain.Membership.MembershipName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class FindMembershipControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper mapper;
  @Autowired
  private RegisterMembershipController registerMembershipController;

  @Test
  public void findMembershipById() throws Exception {
      // given
    RegisterMembershipRequest setUpRequest = new RegisterMembershipRequest("name", "email", "address", true);
    Membership expect = registerMembershipController.registerMembership(setUpRequest).getBody();

      // when then
    mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v1/membership/" + expect.getMembershipId())
        ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(expect)));
  }

}