package com.essaid.context.spring;

public interface IConfig {
  
  boolean isCreateScopeContext();
  
  boolean isCreateThreadContext();
  
  boolean isCreateThreadContextList();
  
  boolean isScopeThreadInheritable();
  
  IConfig getParentConfig();
  
  Boolean getAutoCreateScopeContext();
  
  IConfig setAutoCreateScopeContext(Boolean autoCreateScopeContext);
  
  Boolean getAutoCreateThreadContext();
  
  IConfig setAutoCreateThreadContext(Boolean autoCreateThreadContext);
  
  Boolean getAutoCreateThreadContextList();
  
  IConfig setAutoCreateThreadContextList(Boolean autoCreateThreadContextList);
  
  Boolean getScopeThreadInheritable();
  
  IConfig setScopeThreadInheritable(Boolean scopeThreadInheritable);
}
