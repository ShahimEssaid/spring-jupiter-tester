package com.essaid.context.spring;

public interface IConfig {
  
  boolean isCreateScopeContext();
  
  boolean isCreateThreadContext();
  
  boolean isCreateThreadContextList();
  
  boolean isScopeThreadInheritable();
  
  IConfig getParentConfig();
  
  Boolean getAutoCreateScopeContext();
  
  boolean isClearScopeContextsOnClose();
  
  boolean isRegisterShutdownHook();
  
  boolean isCloseSpringContextIfNeeded();
  
  boolean isClearContainersOnClose();
  
  IConfig setAutoCreateScopeContext(Boolean autoCreateScopeContext);
  
  Boolean getAutoCreateThreadContext();
  
  IConfig setAutoCreateThreadContext(Boolean autoCreateThreadContext);
  
  Boolean getAutoCreateThreadContextList();
  
  IConfig setAutoCreateThreadContextList(Boolean autoCreateThreadContextList);
  
  Boolean getScopeThreadInheritable();
  
  IConfig setScopeThreadInheritable(Boolean scopeThreadInheritable);
  
  IConfig setClearScopeContextsOnClose(Boolean clearScopeContextOnClose);
  
  IConfig setClearContainersOnClose(Boolean clearContainersOnClose);
  
  IConfig setCloseSpringContextIfNeeded(Boolean closeSpringContextIfNeeded);
  
  IConfig setRegisterShutdownHook(Boolean registerShutdownHook);
  
  IConfig setCloseSpringContextDelay(long delayMilliseconds);
  
  Boolean getClearScopeContextsOnClose();
  
  Boolean getRegisterShutdownHook();
  
  Boolean getCloseSpringContextIfNeeded();
  
  Boolean getClearContainersOnClose();
  
  long getCloseSpringContextDelay();
}
