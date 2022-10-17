package com.essaid.picocli;

import com.essaid.picocli.support.IntegrationBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import static org.assertj.core.api.Assertions.*;


//@SpringBootTest(classes = TempTests.TempConfiguration.class)
public class TempTests extends IntegrationBase implements ApplicationContextAware {
  
  private ApplicationContext context;
  
  @Test
  void springContextTest(){
    Assertions.assertNotNull(context);
//    A a = context.getBean(A.class);
//    Assertions.assertNotNull(a);
//    Assertions.assertTrue(A.class.isAssignableFrom(a.getClass()));
    ApplicationContext parent = context.getParent();
    if(parent != null){
      System.out.println("Parent context name: " + parent.getApplicationName());
    }
    System.out.println("====== context name: "+ context.getDisplayName());
    String baseHello = context.getBean("baseHello", String.class);
    
    
    //String helloBean = context.getBean("helloBean", String.class);
    //Assertions.assertNotNull(helloBean);
    
//    contextRunner.run(context1 -> {
//      assertThat(context1).hasSingleBean(String.class);
//    }
//    );
  }
  
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
  this.context = applicationContext;
  }
  
  //@Configuration
  @Import(A.class)
  public static class TempConfiguration{
  
  }
  
  @Component
  public static class A {
  
  }
  
}
