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
  
  void setAutoCreateScopeContext(Boolean autoCreateScopeContext);
  
  void setAutoCreateThreadContext(Boolean autoCreateThreadContext);
  
  void setAutoCreateThreadContextList(Boolean autoCreateThreadContextList);
  
  void setScopeThreadInheritable(Boolean scopeThreadInheritable);
}
