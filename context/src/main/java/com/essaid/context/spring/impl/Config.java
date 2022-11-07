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
  @Getter
  private Boolean clearScopeContextsOnClose;
  @Getter
  private Boolean registerShutdownHook;
  @Getter
  private Boolean closeSpringContextIfNeeded;
  @Getter
  private Boolean clearContainersOnClose;
  @Getter
  private long closeSpringContextDelay;
  
  public Config(IConfig parentConfig) {
    this.parentConfig = parentConfig;
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
  public boolean isClearScopeContextsOnClose() {
    if (clearScopeContextsOnClose != null) {
      return clearScopeContextsOnClose;
    }
    if (parentConfig != null) {
      return parentConfig.isClearScopeContextsOnClose();
    }
    return false;
  }
  
  @Override
  public boolean isRegisterShutdownHook() {
    if (registerShutdownHook != null) {
      return registerShutdownHook;
    }
    if (parentConfig != null) {
      return parentConfig.isRegisterShutdownHook();
    }
    return false;
  }
  
  @Override
  public boolean isCloseSpringContextIfNeeded() {
    if (closeSpringContextIfNeeded != null) {
      return closeSpringContextIfNeeded;
    }
    if (parentConfig != null) {
      return parentConfig.isCloseSpringContextIfNeeded();
    }
    return false;
  }
  
  @Override
  public boolean isClearContainersOnClose() {
    if (clearContainersOnClose != null) {
      return clearContainersOnClose;
    }
    if (parentConfig != null) {
      return parentConfig.isClearContainersOnClose();
    }
    return false;
  }
  
  // =============================
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
  
  @Override
  public IConfig setClearScopeContextsOnClose(Boolean clearScopeContextsOnClose) {
    this.clearScopeContextsOnClose = clearScopeContextsOnClose;
    return this;
  }
  
  @Override
  public IConfig setClearContainersOnClose(Boolean clearContainersOnClose) {
    this.clearContainersOnClose = clearContainersOnClose;
    return this;
  }
  
  
  @Override
  public IConfig setCloseSpringContextIfNeeded(Boolean closeSpringContextIfNeeded) {
    this.closeSpringContextIfNeeded = closeSpringContextIfNeeded;
    return this;
  }
  
  @Override
  public IConfig setRegisterShutdownHook(Boolean registerShutdownHook) {
    this.registerShutdownHook = registerShutdownHook;
    return this;
  }
  
  
  @Override
  public IConfig setCloseSpringContextDelay(long closeSpringContextDelay) {
    this.closeSpringContextDelay = closeSpringContextDelay;
    return this;
  }
}
