package com.essaid.context.spring.impl;

import com.essaid.context.spring.IConfig;
import lombok.Getter;

public class Config implements IConfig {
  
  @Getter
  private final IConfig parentConfig;
  @Getter
  private volatile Boolean autoCreateScopeContext;
  @Getter
  private volatile Boolean autoCreateThreadContext;
  @Getter
  private volatile Boolean autoCreateThreadContextList;
  @Getter
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
  
  @Override
  public IConfig setAutoCreateScopeContext(Boolean autoCreateScopeContext) {
    this.autoCreateScopeContext = autoCreateScopeContext;
    return this;
  }
  
  @Override
  public IConfig setAutoCreateThreadContext(Boolean autoCreateThreadContext) {
    this.autoCreateThreadContext = autoCreateThreadContext;
    return this;
  }
  
  @Override
  public IConfig setAutoCreateThreadContextList(Boolean autoCreateThreadContextList) {
    this.autoCreateThreadContextList = autoCreateThreadContextList;
    return this;
  }
  
  @Override
  public IConfig setScopeThreadInheritable(Boolean scopeThreadInheritable) {
    this.scopeThreadInheritable = scopeThreadInheritable;
    return this;
  }
}
