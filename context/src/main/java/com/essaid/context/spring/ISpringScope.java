package com.essaid.context.spring;

import org.springframework.beans.factory.config.Scope;
import org.springframework.context.ConfigurableApplicationContext;

public interface ISpringScope extends Scope {
  
  String getScopeName();
  
  int getScopeOrder();
  
  ConfigurableApplicationContext getScopeApplicationContext();
  
  ISpringScopeData getScopeData();
  
}
