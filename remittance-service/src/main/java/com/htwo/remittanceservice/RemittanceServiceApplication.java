package com.htwo.remittanceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.htwo.remittanceservice", "com.htwo.common"})
public class RemittanceServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(RemittanceServiceApplication.class, args);
  }

}
