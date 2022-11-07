package com.essaid.context.spring;

import org.springframework.context.ConfigurableApplicationContext;

public interface IDomain {
  
  String getDomainName();
  
  IFactory getFactory();
  
//  IThreadManager getThreadManager();
  
  IScope getApplicationScope();
  
  IContainer registerSpringContext(ConfigurableApplicationContext context);
  
  
  void initialize();
  
  void closeDomain();
  
  IConfig getConfig();
}
