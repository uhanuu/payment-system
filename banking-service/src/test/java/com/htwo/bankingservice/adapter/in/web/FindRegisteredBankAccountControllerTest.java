package com.htwo.bankingservice.adapter.in.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.htwo.bankingservice.adapter.out.persistence.RegisteredBankAccountJpaEntity;
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
class FindRegisteredBankAccountControllerTest {

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
  public void findRegisteredBankAccount() throws Exception {
      // given
    String membershipId = "1";
    String bankAccountNumber = "123-456-789";
    BankType bankType = BankType.KB;
    boolean linkedStatusIsValid = true;
    RegisteredBankAccountJpaEntity registeredBankAccount = createAndSaveRegisteredBankAccount(
        membershipId, bankAccountNumber, bankType, linkedStatusIsValid
    );

    // when // then
    mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/banking/account/" + membershipId)
                .contentType(MediaType.APPLICATION_JSON)
                .param("membershipId", membershipId)
        ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.registeredBankAccountId")
            .value(registeredBankAccount.getRegisteredBankAccountId())
        )
        .andExpect(MockMvcResultMatchers.jsonPath("$.membershipId").value(membershipId))
        .andExpect(MockMvcResultMatchers.jsonPath("$.bankAccountNumber").value(bankAccountNumber))
        .andExpect(MockMvcResultMatchers.jsonPath("$.bankType").value(bankType.name()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.linkedStatusIsValid").value(linkedStatusIsValid));
  }

  private RegisteredBankAccountJpaEntity createAndSaveRegisteredBankAccount(
      String membershipId,
      String bankAccountNumber,
      BankType bankType,
      boolean linkedStatusIsValid
  ) {
    final RegisteredBankAccountJpaEntity registeredBankAccountJpaEntity = RegisteredBankAccountJpaEntity.builder()
        .membershipId(membershipId)
        .bankAccountNumber(bankAccountNumber)
        .bankType(bankType)
        .linkedStatusIsValid(linkedStatusIsValid)
        .build();

    return registeredBankAccountJpaRepository.save(registeredBankAccountJpaEntity);
  }

}