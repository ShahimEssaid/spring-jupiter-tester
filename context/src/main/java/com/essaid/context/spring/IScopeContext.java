package com.essaid.context.spring;

import org.springframework.beans.factory.config.Scope;

public interface IScopeContext extends Scope {
  
  IScopeContextInternal internal();
  
  interface IScopeContextInternal extends IScopeContext {
    String getId();
    
    IScope getScope();
    
    String getName();
    
    void setName(String name);
    
    // older
    
    void close();
    
    boolean isClosed();
    
    boolean isNamed();
    
    IScopeContextInternal internal();
    
    
  }
}
