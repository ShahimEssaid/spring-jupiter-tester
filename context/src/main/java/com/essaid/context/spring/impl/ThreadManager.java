package com.essaid.context.spring.impl;

import com.essaid.context.spring.IApplicationDomain;
import com.essaid.context.spring.IThreadContext;
import com.essaid.context.spring.IThreadManager;
import org.springframework.core.NamedThreadLocal;

public class ThreadManager implements IThreadManager {
  
  private final NamedThreadLocal<IThreadContext> threadContextHolder = new NamedThreadLocal<>("Spring thread context");
  
  private final IApplicationDomain domain;
  
  public ThreadManager(IApplicationDomain domain){
    this.domain = domain;
  }
  
  
  @Override
  public IThreadContext getThreadContext(boolean create) {
    IThreadContext context = threadContextHolder.get();
    if (context == null && create) {
      context = domain.getFactory().createThreadContext();
      threadContextHolder.set(context);
    }
    return context;
  }
  
  @Override
  public IThreadContext setThreadContext(IThreadContext context, boolean overwrite) {
    IThreadContext existingContext = getThreadContext(false);
    if (existingContext != null && !overwrite) {
      throw new IllegalStateException(
          "Can't overwrite thread context for application domain: " + this + ", current context: " + existingContext + ", and new context:" + context);
    }
    threadContextHolder.set(context);
    return existingContext;
  }
  
  
  @Override
  public IThreadContext removeThreadContext() {
    IThreadContext threadContext = getThreadContext(false);
    threadContextHolder.remove();
    return threadContext;
  }

}
