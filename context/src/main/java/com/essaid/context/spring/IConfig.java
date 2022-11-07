package com.essaid.context.spring;

public interface IConfig {
  
  boolean isCreateScopeContext();
  
  boolean isCreateThreadContext();
  
  boolean isCreateThreadContextList();
  
  boolean isScopeThreadInheritable();
  
  IConfig getParentConfig();
  
  Boolean getAutoCreateScopeContext();
  
  Boolean getAutoCreateThreadContext();
  
  Boolean getAutoCreateThreadContextList();
  
  Boolean getScopeThreadInheritable();
  
  IConfig setAutoCreateScopeContext(Boolean autoCreateScopeContext);
  
  IConfig setAutoCreateThreadContext(Boolean autoCreateThreadContext);
  
  IConfig setAutoCreateThreadContextList(Boolean autoCreateThreadContextList);
  
  IConfig setScopeThreadInheritable(Boolean scopeThreadInheritable);
}
