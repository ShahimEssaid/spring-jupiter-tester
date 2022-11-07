package com.essaid.context.spring;

import org.springframework.beans.factory.config.Scope;

public interface IScopeContext extends Scope {
  
  String getId();
  
  IScope getScope();
  
  String getName();
  
  void setName(String name);
  
  // older
  
  void close();
  
  boolean isClosed();
  
  boolean isNamed();
  
}
