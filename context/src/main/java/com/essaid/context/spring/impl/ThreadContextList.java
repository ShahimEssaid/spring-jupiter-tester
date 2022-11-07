package com.essaid.context.spring.impl;

import com.essaid.context.spring.IDomain;
import com.essaid.context.spring.IThreadContext;
import com.essaid.context.spring.IThreadContextList;
import com.essaid.util.asserts.Asserts;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter
@Setter
public class ThreadContextList implements IThreadContextList {
  private final LinkedList<IThreadContext> contexts = new LinkedList<>();
  
  @Override
  public IThreadContext peekContext() {
    return contexts.peek();
  }
  
  @Override
  public IThreadContext popContext() {
    return contexts.pop();
  }
  
  @Override
  public void pushContext(IThreadContext context) {
    Asserts.notNull("Can't push a null IContext onto IThreadContext: ", this);
    contexts.push(context);
  }
  
  @Override
  public boolean isEmpty() {
    return contexts.isEmpty();
  }
  
  @Override
  public int size() {
    return contexts.size();
  }
}
