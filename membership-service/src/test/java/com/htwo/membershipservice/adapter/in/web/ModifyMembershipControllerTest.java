package com.htwo.membershipservice.adapter.in.web;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
class ModifyMembershipControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper mapper;
  @Autowired
  private RegisterMembershipController registerMembershipController;
  @Autowired
  private FindMembershipController findMembershipController;
  @Autowired
  private MembershipJpaRepository membershipJpaRepository;

  @BeforeEach
  void setup(){
    membershipJpaRepository.deleteAllInBatch();
  }

  @Test
  public void modifyMembership() throws Exception {
    // given
    RegisterMembershipRequest setUpRequest = new RegisterMembershipRequest("name", "email", "address", true);
    Membership setUp = registerMembershipController.registerMembership(setUpRequest).getBody();

    ModifyMembershipRequest request = new ModifyMembershipRequest(
        "updateName",
        "updateEmail",
        "updateAddress",
        false,
        false
    );
    Membership expect = Membership.generateMember(
        new MembershipId(String.valueOf(1)),
        new MembershipName("updateName"),
        new MembershipEmail("updateEmail"),
        new MembershipAddress("updateAddress"),
        new MembershipIsValid(false),
        new MembershipIsCorp(false));

    // when then
    mockMvc.perform(
        MockMvcRequestBuilders.put("/api/v1/membership/" + setUp.getMembershipId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request))
    ).andExpect(MockMvcResultMatchers.status().isOk());

    // 변경감지 테스트
    Membership findMembership = findMembershipController.findMembershipById(setUp.getMembershipId()).getBody();
    assertAll(
        () -> assertEquals(expect.getName(), findMembership.getName()),
        () -> assertEquals(expect.getEmail(), findMembership.getEmail()),
        () -> assertEquals(expect.getAddress(), findMembership.getAddress()),
        () -> assertEquals(expect.isValid(), findMembership.isValid()),
        () -> assertEquals(expect.isCorp(), findMembership.isCorp())
    );
  }
}