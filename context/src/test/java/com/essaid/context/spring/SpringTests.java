package com.essaid.context.spring;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tmp.springboot.example1.comp.ApplicationBeanA;
import tmp.springboot.example1.comp.RequestBeanA;
import tmp.springboot.example1.comp.SessionBeanA;
import tmp.springboot.example1.comp.SingletonBeanA;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SpringTests {
  
  private IDomain domain;
  private AnnotationConfigApplicationContext context;
  private IContainer container;
  private IThreadContextList threadContextList;
  
  
  @Test
  @Order(1)
  void setupDomain() {
    domain = IFactory.DEFAULT_FACTORY.createContextDomain();
    domain.getConfig().setRegisterShutdownHook(true).setCloseSpringContextDelay(5000)
        .setCloseSpringContextIfNeeded(false);
    domain.registerShutdownHook();
  }
  
  @Test
  @Order(2)
  void createContext() {
    context = new AnnotationConfigApplicationContext();
    context.register(ApplicationBeanA.class, RequestBeanA.class, SessionBeanA.class, SingletonBeanA.class);
    //context.registerShutdownHook();
    container = domain.registerSpringContext(context, null);
    threadContextList = domain.getThreadManager().getThreadContextList(domain, container.getConfig());
    container.createDefaultScopes();
    context.refresh();
    
  }
  
  @Test
  @Order(3)
  void getBeans() {
    IThreadContextList threadContextList1 = domain.getThreadManager()
        .getThreadContextList(domain, container.getConfig());
    
    ApplicationBeanA applicationBeanA = context.getBean(ApplicationBeanA.class);
    RequestBeanA requestBeanA1 = applicationBeanA.getRequestBeanA();
    requestBeanA1.setString("requestBeanA");
    applicationBeanA.setString("applicationBeanA");
    
    
    SessionBeanA sessionBeanA = context.getBean(SessionBeanA.class);
    sessionBeanA.setString("session");
    
    Assertions.assertThat(sessionBeanA.getRequestBeanA().getString()).isEqualTo("requestBeanA");
    Assertions.assertThat(sessionBeanA.getApplicationBeanA().getString()).isEqualTo("applicationBeanA");
    
    RequestBeanA requestBeanA = context.getBean(RequestBeanA.class);
    Assertions.assertThat(context.getBean(RequestBeanA.class).getString()).isEqualTo("requestBeanA");
    
    domain.registerShutdownHook();
    // context.close();
    // domain.closeDomain();
    
    System.out.println("Out");
  }

//  @Test
//  @Order(1)
//  void startDomain() {
//    domain = Scopes.createDomain("Boot 2", true, true, true, true);
//    domain.initialize();
//    Scopes.setDomain(domain, true);
//    Assertions.assertThat(Scopes.getDomain()).isNotNull();
//    Thread thread = Thread.currentThread();
//    System.out.println("============= THREAD:" + Thread.currentThread());
//  }

//  @Test
//  @Order(2)
//  void startBoot(){
//
//    Assertions.assertThat(Scopes.getDomain()).isNotNull();
//    bootApplication = new SpringApplication(Boot.class);
//    bootApplication.addInitializers(new ApplicationContextInitializer<ConfigurableApplicationContext>() {
//      @Override
//      public void initialize(ConfigurableApplicationContext applicationContext) {
//        // application
//        IScope scope = Scopes.createApplicationScope(domain, applicationContext);
//        applicationContext.getBeanFactory().registerScope(scope.getScopeName(), scope);
//        // container
//        scope = Scopes.createContainerScope(domain, applicationContext);
//        // session
//        scope = Scopes.createSessionScope(domain, applicationContext, scope);
//        applicationContext.getBeanFactory().registerScope(scope.getScopeName(), scope);
//        // request
//        scope = Scopes.createRequestScope(domain, applicationContext, scope);
//        applicationContext.getBeanFactory().registerScope(scope.getScopeName(), scope);
////
////        // application
////        applicationContext.getBeanFactory().registerScope(domain.getScopeName(), domain);
//        applicationContext.addApplicationListener(domain);
//      }
//
//    });
//    context = bootApplication.run(new String[]{});
//
//  }

//  @Test
//  @Order(3)
//  void testBeans() {
//
//    Assertions.assertThat(context.getBean(ApplicationBeanA.class)).isNotNull().isInstanceOfAny(ApplicationBeanA.class);
//    Assertions.assertThat(context.getBean(SessionBeanA.class)).isNotNull().isInstanceOfAny(SessionBeanA.class);
//    Assertions.assertThat(context.getBean(RequestBeanA.class)).isNotNull().isInstanceOfAny(RequestBeanA.class);
//  }
  
  
}
