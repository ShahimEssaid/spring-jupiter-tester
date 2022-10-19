package com.essaid.picocliold;

import com.essaid.picocliold.support.IntegrationBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Disabled
public class EnvironmentTests extends IntegrationBase {
  
  @Test
  @Order(0)
  @Disabled
  void getHello() {
    Assertions.assertEquals("Hello", context.getBean("getHello"));
  }
  
  
}


@Configuration
class Config {
  
  @Bean
  String getHello() {
    return "Hello";
  }
}