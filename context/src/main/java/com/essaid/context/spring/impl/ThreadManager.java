package com.essaid.context.spring.impl;

import com.essaid.context.spring.IConfig;
import com.essaid.context.spring.IDomain;
import com.essaid.context.spring.IThreadContext;
import com.essaid.context.spring.IThreadContextList;
import com.essaid.context.spring.IThreadManager;
import org.springframework.core.NamedThreadLocal;

public class ThreadManager implements IThreadManager.IThreadManagerInternal {
  
  private final NamedThreadLocal<IThreadContextList> threadContextListHolder = new NamedThreadLocal<>(
      "Spring thread context");
  
  @Override
  public IThreadContextList getThreadContextList(IDomain domain, IConfig config) {
    IThreadContextList contextList = threadContextListHolder.get();
    if (contextList == null && config.isCreateThreadContextList()) {
      contextList = domain.internal().getFactory().internal().createThreadContextList();
      threadContextListHolder.set(contextList);
    }
    return contextList;
  }
  
  @Override
  public IThreadContextList setThreadContextList(IThreadContextList contextList, boolean overwrite) {
    IThreadContextList existingContextList = threadContextListHolder.get();
    if (existingContextList != null && !overwrite) {
      throw new IllegalStateException(
          "Can't overwrite thread context list for thread manager: " + this + ", current context: " + existingContextList + ", and new context:" + contextList);
    }
    threadContextListHolder.set(contextList);
    return existingContextList;
  }
  
  @Override
  public IThreadContext getThreadContext(IDomain domain, IConfig config) {
    IThreadContextList threadContextList = getThreadContextList(domain, config);
    if (threadContextList.internal().isEmpty() && config.isCreateThreadContext()) {
      IThreadContext threadContext = domain.internal().getFactory().internal().createThreadContext(domain, config);
      threadContextList.internal().pushContext(threadContext);
    }
    return threadContextList.internal().peekContext();
  }
  
  
  @Override
  public IThreadContextList removeThreadContextList() {
    IThreadContextList threadContextList = threadContextListHolder.get();
    threadContextListHolder.remove();
    return threadContextList;
  }
  
  @Override
  public IThreadManagerInternal internal() {
    return this;
  }
}
