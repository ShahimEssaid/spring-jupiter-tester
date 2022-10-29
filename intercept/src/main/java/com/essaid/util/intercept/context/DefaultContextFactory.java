package com.essaid.util.intercept.context;

import com.essaid.util.asserts.Asserts;
import com.essaid.util.intercept.data.IData;
import com.essaid.util.intercept.domain.IDomain;
import com.essaid.util.intercept.interceptor.IInterceptor;
import com.essaid.util.intercept.interceptor.IInterceptorGroup;
import com.essaid.util.intercept.interceptor.IInterceptorList;

import java.util.Collections;
import java.util.Iterator;

public class DefaultContextFactory implements IContextFactory {
  
  private final IDomain domain;
  
  public DefaultContextFactory(IDomain domain){
    this.domain = domain;
  }
  
  @Override
  public IInterceptorContext createContext(IDomain domain, IData data, IData localData, IInterceptor interceptor) {
    Asserts.allNotNull("Null argument wile constructing context", domain, data, localData, interceptor);
    if (IInterceptorGroup.class.isAssignableFrom(interceptor.getClass())) {
      if (IInterceptorList.class.isAssignableFrom(interceptor.getClass())) {
        IInterceptorList listInterceptor = (IInterceptorList) interceptor;
        Iterator<IInterceptor> iterator = listInterceptor.getIterator();
        return new IteratorInterceptorContext(domain, data, localData, iterator);
      } else {
        throw new UnsupportedOperationException("This interceptor group is not supported yet: " + interceptor.getClass());
      }
    } else {
      // simple interceptor
      return new IteratorInterceptorContext(domain, data, localData, Collections.singletonList(interceptor).iterator());
    }
  }
}
