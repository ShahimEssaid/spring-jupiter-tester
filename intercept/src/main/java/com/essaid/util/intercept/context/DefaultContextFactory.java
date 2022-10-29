package com.essaid.util.intercept.context;

import com.essaid.util.intercept.domain.IDomain;
import com.essaid.util.intercept.interceptor.IInterceptor;
import com.essaid.util.intercept.interceptor.IInterceptorGroup;
import com.essaid.util.intercept.interceptor.IInterceptorList;
import com.essaid.util.model.IModel;

import java.util.Collections;
import java.util.Iterator;

public class DefaultContextFactory implements IContextFactory {
  
  @Override
  public IInterceptorContext createContext(IDomain domain, IModel data, IModel localData,
                                           IInterceptor interceptor) {
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
