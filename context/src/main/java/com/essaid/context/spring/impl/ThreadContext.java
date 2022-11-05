package com.essaid.context.spring.impl;

import com.essaid.context.spring.IApplicationDomain;
import com.essaid.context.spring.IContext;
import com.essaid.context.spring.IThreadContext;
import com.essaid.util.asserts.Asserts;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter
@Setter
public class ThreadContext implements IThreadContext {
  private final LinkedList<IContext> contexts = new LinkedList<>();
  private final IApplicationDomain domain;
  
  public ThreadContext(IApplicationDomain domain) {
    this.domain = domain;
  }
  
  @Override
  public IContext peekContext(boolean autoContext) {
    IContext context = contexts.peek();
    if (context == null && autoContext) {
      context = domain.getFactory().createContext();
      pushContext(context);
    }
    return context;
  }
  
  @Override
  public IContext popContext() {
    return contexts.pop();
  }
  
  @Override
  public void pushContext(IContext context) {
    Asserts.notNull("Can't push a null IContext onto IThreadContext: ", this);
    contexts.push(context);
  }
  
  @Override
  public boolean isEmpty() {
    return contexts.isEmpty();
  }
}
