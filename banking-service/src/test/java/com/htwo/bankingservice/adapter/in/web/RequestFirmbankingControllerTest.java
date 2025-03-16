package com.htwo.bankingservice.adapter.in.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.htwo.bankingservice.adapter.out.persistence.RequestFirmbankingJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
class RequestFirmbankingControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper mapper;
  @Autowired
  private RequestFirmbankingJpaRepository requestFirmbankingJpaRepository;

  @BeforeEach
  void setup(){
    requestFirmbankingJpaRepository.deleteAllInBatch();
  }

  @Test
  public void requestFirmBanking() throws Exception {
      // given
    RequestFirmbankingRequest request = new RequestFirmbankingRequest("우리은행", "123-456", "국민은행",
        "456-789", "50000");
    // when // then
    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/v1/banking/firm-banking")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request))
    ).andExpect(MockMvcResultMatchers.status().isOk());
  }
}