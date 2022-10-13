package com.essaid.picocli;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration( name = "tempTests", classes = TempTests.TempConfiguration.class)
public class TempTests implements ApplicationContextAware {
  
  private ApplicationContext context;
  
  @Test
  void springContextTest(){
    Assertions.assertNotNull(context);
    A a = context.getBean(A.class);
    Assertions.assertNotNull(a);
    Assertions.assertTrue(A.class.isAssignableFrom(a.getClass()));
  }
  
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
  this.context = applicationContext;
  }
  
  @Configuration
  @Import(A.class)
  public static class TempConfiguration{
  
  }
  
  @Component
  public static class A {
  
  }
  
}
