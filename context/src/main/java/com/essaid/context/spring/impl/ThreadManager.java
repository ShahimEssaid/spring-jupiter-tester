package com.essaid.context.spring.impl;

import com.essaid.context.spring.IConfig;
import com.essaid.context.spring.IContainer;
import com.essaid.context.spring.IThreadContext;
import com.essaid.context.spring.IThreadContextList;
import com.essaid.context.spring.IThreadManager;
import org.springframework.core.NamedThreadLocal;

public class ThreadManager implements IThreadManager {
  
  private final NamedThreadLocal<IThreadContextList> threadContextListHolder = new NamedThreadLocal<>(
      "Spring thread context");
  
  @Override
  public IThreadContextList getThreadContextList(IContainer container,IConfig config) {
    IThreadContextList contextList = threadContextListHolder.get();
    if (contextList == null && config.isCreateThreadContextList()) {
      contextList = container.getFactory().createThreadContextList();
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
  public IThreadContextList removeThreadContextList() {
    IThreadContextList threadContextList = threadContextListHolder.get();
    threadContextListHolder.remove();
    return threadContextList;
  }
  
  @Override
  public IThreadContext getContext(IContainer container, IConfig config) {
    IThreadContextList threadContextList = getThreadContextList(container, config);
    IThreadContext threadContext = null;
    if (threadContextList.isEmpty() && config.isCreateThreadContext()) {
      threadContext = container.getFactory().createThreadContext(container);
      threadContextList.pushContext(threadContext);
    }
    return threadContext;
  }
  
}
