package com.essaid.picocli.commands.support;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@EnableAutoConfiguration
@SpringBootTest(classes = IntegrationBase.BaseConfiguration.class)
//@ContextConfiguration(name = "base-context",classes = IntegrationBase.BaseConfiguration.class)
public class IntegrationBase extends TestBase implements ApplicationContextAware {
  
  protected ApplicationContext context;
  
  protected ApplicationContextRunner contextRunner = new ApplicationContextRunner();
  
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.context = applicationContext;
  }
  
  @Configuration
  static class BaseConfiguration{
    
    @Bean
    String baseHello(){
      return "Base hello";
    }
  
  }
}
