package com.essaid.picocli;

import com.essaid.picocli.support.IntegrationBase;
import com.essaid.picocli.support.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = Config.class)
public class EnvironmentTests extends IntegrationBase {
  
  @Test
  @Order(0)
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