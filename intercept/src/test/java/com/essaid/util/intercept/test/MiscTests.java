package com.essaid.util.intercept.test;

import com.essaid.util.intercept.IInterceptor;
import com.essaid.util.intercept.InterceptorOrder;
import com.essaid.util.intercept.MyDomain;
import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.intercept.domain.Domain;
import com.essaid.util.intercept.group.IInterceptorGroup;
import com.essaid.util.intercept.group.InterceptorList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MiscTests {
  
  @Test
  void tryListGroup() {
  
    List<IInterceptor<? extends  Domain>> interceptors = new ArrayList<>();
    interceptors.add(new Hello());
    interceptors.add(new There());

    IInterceptorGroup<Domain> group = new InterceptorList<>(new Domain(), interceptors);

    group.doInterceptor(null);
  }
  
  @InterceptorOrder(1)
  static class Hello implements IInterceptor<Domain> {
    @Override
    public String doInterceptor(IInterceptorContext interceptorContext) {
      System.out.println("Hello called");
      interceptorContext.doNextInterceptor();
      return "Hello";
    }
  }
  
  @InterceptorOrder(2)
  static class There implements IInterceptor<MyDomain> {
  
    @Override
    public Object doInterceptor(IInterceptorContext interceptorContext) {
      System.out.println("There called");
      interceptorContext.doNextInterceptor();
      return "There";
    }
  }
}
