package com.htwo.bankingservice.adapter.in.web;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.htwo.bankingservice.adapter.out.persistence.RegisteredBankAccountJpaRepository;
import com.htwo.bankingservice.domain.BankType;
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
class RegisterBankAccountControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper mapper;
  @Autowired
  private RegisteredBankAccountJpaRepository registeredBankAccountJpaRepository;

  @BeforeEach
  void setup(){
    registeredBankAccountJpaRepository.deleteAllInBatch();
  }

  @Test
  public void registerMembership() throws Exception {
    // given
    RegisterBankAccountRequest registerBankAccountRequest = new RegisterBankAccountRequest(
        "1", "12345678", BankType.KB, true
    );
    // when then
    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/v1/banking/account")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(registerBankAccountRequest))
    ).andExpect(MockMvcResultMatchers.status().isOk());
//        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expect.getName()))
//        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(expect.getEmail()))
//        .andExpect(MockMvcResultMatchers.jsonPath("$.address").value(expect.getAddress()))
//        .andExpect(MockMvcResultMatchers.jsonPath("$.valid").value(expect.isValid()))
//        .andExpect(MockMvcResultMatchers.jsonPath("$.corp").value(expect.isCorp()));
//        .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(expect)));
  }

}