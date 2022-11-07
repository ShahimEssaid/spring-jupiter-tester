package com.essaid.context.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public interface IDomain {
  
  String DEFAULT_DOMAIN_NAME = "_default_domain";
  
  String getDomainName();
  
  IFactory getFactory();

//  IThreadManager getThreadManager();
  
  IScope getApplicationScope();
  
  IContainer registerSpringContext(ConfigurableApplicationContext context);
  
  IContainer unregisterSpringContext(ConfigurableApplicationContext context);
  
  
  void initialize();
  
  void closeDomain();
  
  IConfig getConfig();
  
  IThreadContextList getThreadContextList(IConfig config);
  
  IThreadContextList setThreadContextList(IThreadContextList context, boolean overwrite);
  
  IThreadContext getContext( IConfig config);
  
  IThreadContextList removeThreadContextList();
  
  
}
