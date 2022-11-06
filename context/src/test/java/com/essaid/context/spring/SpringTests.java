package com.essaid.context.spring;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import tmp.springboot.example1.Boot;
import tmp.springboot.example1.comp.ApplicationBeanA;
import tmp.springboot.example1.comp.RequestBeanA;
import tmp.springboot.example1.comp.SessionBeanA;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SpringTests {
  
  private IApplicationDomain domain;
  private SpringApplication bootApplication;
  private ConfigurableApplicationContext context;
  
  @Test
  @Order(1)
  void startDomain() {
    domain = Scopes.createDomain("Boot 2", true, true, true, true);
    domain.initialize();
    Scopes.setDomain(domain, true);
    Assertions.assertThat(Scopes.getDomain()).isNotNull();
    Thread thread = Thread.currentThread();
    System.out.println("============= THREAD:" + Thread.currentThread());
  }
  
  @Test
  @Order(2)
  void startBoot(){

    Assertions.assertThat(Scopes.getDomain()).isNotNull();
    bootApplication = new SpringApplication(Boot.class);
    bootApplication.addInitializers(new ApplicationContextInitializer<ConfigurableApplicationContext>() {
      @Override
      public void initialize(ConfigurableApplicationContext applicationContext) {
        // application
        IScope scope = Scopes.createApplicationScope(domain, applicationContext);
        applicationContext.getBeanFactory().registerScope(scope.getScopeName(), scope);
        // container
        scope = Scopes.createContainerScope(domain, applicationContext);
        // session
        scope = Scopes.createSessionScope(domain, applicationContext, scope);
        applicationContext.getBeanFactory().registerScope(scope.getScopeName(), scope);
        // request
        scope = Scopes.createRequestScope(domain, applicationContext, scope);
        applicationContext.getBeanFactory().registerScope(scope.getScopeName(), scope);
//
//        // application
//        applicationContext.getBeanFactory().registerScope(domain.getScopeName(), domain);
        applicationContext.addApplicationListener(domain);
      }
      
    });
    context = bootApplication.run(new String[]{});
    
  }
  
  @Test
  @Order(3)
  void testBeans(){
  
    Assertions.assertThat(context.getBean(ApplicationBeanA.class)).isNotNull().isInstanceOfAny(ApplicationBeanA.class);
    Assertions.assertThat(context.getBean(SessionBeanA.class)).isNotNull().isInstanceOfAny(SessionBeanA.class);
    Assertions.assertThat(context.getBean(RequestBeanA.class)).isNotNull().isInstanceOfAny(RequestBeanA.class);
  }
  
  
}
