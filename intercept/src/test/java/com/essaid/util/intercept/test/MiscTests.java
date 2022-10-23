package com.essaid.util.intercept.test;

import com.essaid.util.intercept.IInterceptor;
import com.essaid.util.intercept.InterceptorOrder;
import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.intercept.context.ListInterceptorContext;
import com.essaid.util.intercept.data.DomainData;
import com.essaid.util.intercept.domain.Domain;
import com.essaid.util.intercept.domain.IDomain;
import com.essaid.util.intercept.group.IInterceptorGroup;
import com.essaid.util.intercept.group.InterceptorList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MiscTests<D extends IDomain, R extends String, C extends ListInterceptorContext<D, R, C>> {
  
  @Test
  void tryListGroup() {
  
    List<IInterceptor<IDomain, String, ? extends C>> interceptors = new ArrayList<>();
    interceptors.add(new Hello<C>());
//    interceptors.add(new There<>());
//
//    IInterceptorGroup<IDomain, Object, C> group =
//        new InterceptorList<>(new Domain(), interceptors);
//
//    group.doInterceptor(null);
    
  }
  
  @InterceptorOrder(1)
  static class Hello<C extends IInterceptorContext<IDomain, String, C>> implements IInterceptor<IDomain, String, C> {
    @Override
    public String doInterceptor(IInterceptorContext<IDomain, String, C> interceptorContext) {
      System.out.println("Hello called");
      interceptorContext.doNextInterceptor();
      return "Hello";
    }
  }
  
  @InterceptorOrder(2)
  static class There<C extends IInterceptorContext<IDomain, Object, C>> implements IInterceptor<IDomain, Object, C> {
  
    @Override
    public Object doInterceptor(IInterceptorContext<IDomain, Object, C> interceptorContext) {
      return "There";
    }
  }
}
