package com.essaid.context.spring;

import org.springframework.context.ConfigurableApplicationContext;

public interface IDomain {
  
  String DEFAULT_DOMAIN_NAME = "_default_domain";
  
  String getDomainName();
  
  IFactory getFactory();

//  IThreadManager getThreadManager();
  
  IScope getApplicationScope();
  
  IContainer registerSpringContext(ConfigurableApplicationContext context);
  
  //IContainer unregisterSpringContext(ConfigurableApplicationContext context);
  
  
  void initialize();
  
  void closeDomain();
  
  IConfig getConfig();
  
  void registerShutdownHook();
  
  IThreadManager getThreadManager();
  
}
