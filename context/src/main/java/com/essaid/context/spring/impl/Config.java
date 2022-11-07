package com.essaid.context.spring.impl;

import com.essaid.context.spring.IConfig;
import lombok.Getter;
import lombok.Setter;

public class Config implements IConfig {
  
  @Getter
  private final IConfig parentConfig;
  
  @Getter
  @Setter
  private volatile Boolean autoCreateScopeContext;
  @Getter
  @Setter
  private volatile Boolean autoCreateThreadContext;
  @Getter
  @Setter
  private volatile Boolean autoCreateThreadContextList;
  @Getter
  @Setter
  private volatile Boolean scopeThreadInheritable;
  
  public Config(IConfig parentConfig) {
    this.parentConfig = parentConfig;
  }
  
  public Config() {
    this.parentConfig = null;
  }
  
  @Override
  public boolean isCreateScopeContext() {
    if (autoCreateScopeContext != null) {
      return autoCreateScopeContext;
    }
    if (parentConfig != null) {
      return parentConfig.isCreateScopeContext();
    }
    return false;
  }
  
  @Override
  public boolean isCreateThreadContext() {
    if (autoCreateThreadContext != null) {
      return autoCreateThreadContext;
    }
    if (parentConfig != null) {
      return parentConfig.isCreateThreadContext();
    }
    return false;
  }
  
  @Override
  public boolean isCreateThreadContextList() {
    if (autoCreateThreadContextList != null) {
      return autoCreateThreadContextList;
    }
    if (parentConfig != null) {
      return parentConfig.isCreateThreadContextList();
    }
    return false;
  }
  
  @Override
  public boolean isScopeThreadInheritable() {
    if (scopeThreadInheritable != null) {
      return scopeThreadInheritable;
    }
    if (parentConfig != null) {
      return parentConfig.isScopeThreadInheritable();
    }
    return false;
  }
}
