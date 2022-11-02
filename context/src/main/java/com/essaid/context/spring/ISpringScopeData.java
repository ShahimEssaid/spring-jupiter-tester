package com.essaid.context.spring;

import org.springframework.beans.factory.config.Scope;

public interface ISpringScopeData extends Scope {
  boolean isActive();
  
  void setActive(boolean active);
  
  void close();
  
  ISpringScope getScope();
}
