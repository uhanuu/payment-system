package com.htwo.membershipservice.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.htwo.membershipservice.adapter.out.persistence.MembershipJpaRepository;
import com.htwo.membershipservice.domain.Membership;
import com.htwo.membershipservice.domain.Membership.MembershipAddress;
import com.htwo.membershipservice.domain.Membership.MembershipEmail;
import com.htwo.membershipservice.domain.Membership.MembershipId;
import com.htwo.membershipservice.domain.Membership.MembershipIsCorp;
import com.htwo.membershipservice.domain.Membership.MembershipIsValid;
import com.htwo.membershipservice.domain.Membership.MembershipName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RegisterMembershipControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper mapper;
  @Autowired
  private MembershipJpaRepository membershipJpaRepository;

  @BeforeEach
  void setup(){
    membershipJpaRepository.deleteAllInBatch();
  }

  @Test
  public void registerMembership() throws Exception {
    // given
    RegisterMembershipRequest request = new RegisterMembershipRequest("name", "email", "address", true);
    Membership expect = Membership.generateMember(
        new MembershipId(String.valueOf(1)),
        new MembershipName("name"),
        new MembershipEmail("email"),
        new MembershipAddress("address"),
        new MembershipIsValid(true),
        new MembershipIsCorp(true));

    // when then
    mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/membership")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
        ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expect.getName()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(expect.getEmail()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.address").value(expect.getAddress()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.valid").value(expect.isValid()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.corp").value(expect.isCorp()));
//        .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(expect)));
  }

}