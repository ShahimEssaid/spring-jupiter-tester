package com.essaid.context.spring;

public interface IConfig {
  
  boolean isCreateScopeContext();
  
  boolean isCreateThreadContext();
  
  boolean isCreateThreadContextList();
  
  boolean isScopeThreadInheritable();
  
  IConfig getParentConfig();
  
  Boolean getAutoCreateScopeContext();
  
  IConfig setAutoCreateScopeContext(Boolean autoCreateScopeContext);
  
  boolean isClearScopeContextsOnClose();
  
  boolean isRegisterShutdownHook();
  
  boolean isCloseSpringContextIfNeeded();
  
  boolean isClearContainersOnClose();
  
  Boolean getAutoCreateThreadContext();
  
  IConfig setAutoCreateThreadContext(Boolean autoCreateThreadContext);
  
  Boolean getAutoCreateThreadContextList();
  
  IConfig setAutoCreateThreadContextList(Boolean autoCreateThreadContextList);
  
  Boolean getScopeThreadInheritable();
  
  IConfig setScopeThreadInheritable(Boolean scopeThreadInheritable);
  
  Boolean getClearScopeContextsOnClose();
  
  IConfig setClearScopeContextsOnClose(Boolean clearScopeContextOnClose);
  
  Boolean getRegisterShutdownHook();
  
  IConfig setRegisterShutdownHook(Boolean registerShutdownHook);
  
  Boolean getCloseSpringContextIfNeeded();
  
  IConfig setCloseSpringContextIfNeeded(Boolean closeSpringContextIfNeeded);
  
  Boolean getClearContainersOnClose();
  
  IConfig setClearContainersOnClose(Boolean clearContainersOnClose);
  
  long getCloseSpringContextDelay();
  
  IConfig setCloseSpringContextDelay(long delayMilliseconds);
  
  IConfigInternal internal();
  
  interface IConfigInternal extends IConfig {
  
  }
}
