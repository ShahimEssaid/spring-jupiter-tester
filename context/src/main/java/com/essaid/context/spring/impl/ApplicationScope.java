package com.essaid.context.spring.impl;

import com.essaid.context.spring.IConfig;
import com.essaid.context.spring.IContainer;
import com.essaid.context.spring.IScopeContext;

public class ApplicationScope extends Scope {
  
  private final IScopeContext applicationScopeContext;
  
  public ApplicationScope(IScopeContext applicationScopeContext, IConfig config) {
    super(null, IContainer.APPLICATION_NAME, IContainer.APPLICATION_ORDER, null, config);
    this.applicationScopeContext = applicationScopeContext;
  }
  
  @Override
  public IScopeContext getScopeContext() {
    return applicationScopeContext;
  }
}
