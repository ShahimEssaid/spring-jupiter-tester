package com.essaid.util.intercept.domain;

import com.essaid.util.model.IModelInterface;
import com.essaid.util.model.IInterfaceable;

public class Domain implements IDomain {
  
  
  private final IInterfaceable proxy;
  
  public Domain(IInterfaceable domainProxy) {
    this.proxy = domainProxy;
  }
  
  @Override
  public <I extends IModelInterface> I as(Class<I> cls) {
    return proxy.as(cls);
  }
}
