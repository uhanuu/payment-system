package com.htwo.membershipservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.htwo.common", "com.htwo.membershipservice"})
public class MembershipServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MembershipServiceApplication.class, args);
  }

}
