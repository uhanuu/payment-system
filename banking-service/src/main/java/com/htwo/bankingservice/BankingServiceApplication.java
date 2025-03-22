package com.htwo.bankingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.htwo.bankingservice", "com.htwo.common"})
public class BankingServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(BankingServiceApplication.class, args);
  }

}
