package com.essaid.context.spring.impl;

import com.essaid.context.spring.IConfig;
import com.essaid.context.spring.IDomain;
import com.essaid.context.spring.IThreadContext;
import com.essaid.context.spring.IThreadContextList;
import com.essaid.context.spring.IThreadManager;
import org.springframework.core.NamedThreadLocal;

public class ThreadManager implements IThreadManager {
  
  private final NamedThreadLocal<IThreadContextList> threadContextListHolder = new NamedThreadLocal<>(
      "Spring thread context");
  
  @Override
  public IThreadContextList getThreadContextList(IDomain domain, IConfig config) {
    IThreadContextList contextList = threadContextListHolder.get();
    if (contextList == null && config.isCreateThreadContextList()) {
      contextList = domain.getFactory().createThreadContextList();
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
    if (threadContextList.isEmpty() && config.isCreateThreadContext()) {
      IThreadContext threadContext = domain.getFactory().createThreadContext(domain, config);
      threadContextList.pushContext(threadContext);
    }
    return threadContextList.peekContext();
  }
  
  
  @Override
  public IThreadContextList removeThreadContextList() {
    IThreadContextList threadContextList = threadContextListHolder.get();
    threadContextListHolder.remove();
    return threadContextList;
  }

}
