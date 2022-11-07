package com.essaid.context.spring;

public interface IClientScopeContextConfig {
  
  String getScopeContextId();
  
  String getScopeContextName();
  
  IThreadContextScopeConfigType getType();
  
  enum IThreadContextScopeConfigType {
    /**
     * Context name must not exist and a new one started with name.
     */
    ENTER_START_ONLY,
    
    /**
     * If context exists, it will be deleted, and a new one started with name.
     */
    ENTER_END_START,
    /**
     * The name exists or is created.
     */
    ENTER_USE_START,
    /**
     * The name must already exist.
     */
    ENTER_USE,
    
    /**
     * The name must already exist and will be ended when exiting the context.
     */
    ENTER_USE_EXIT_END,
    /**
     * End on entering the context.
     */
    ENTER_END
  }
  
  IClientScopeContextConfigInternal internal();
  
  interface IClientScopeContextConfigInternal extends  IClientScopeContextConfig{
  
  }
}
