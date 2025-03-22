package com.htwo.moneyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = {"com.htwo.common", "com.htwo.moneyservice"})
@EnableJpaAuditing
public class MoneyServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MoneyServiceApplication.class, args);
  }

}
