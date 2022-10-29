package com.essaid.util.intercept.test;

import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.intercept.domain.IDomain;
//import com.essaid.util.intercept.domain.impl.DomainBeansPropertyProxy;
import com.essaid.util.intercept.interceptor.IInterceptor;
import com.essaid.util.intercept.interceptor.IInterceptorOutcome;
import com.essaid.util.intercept.interceptor.Interceptor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MiscTests {
  
  @Test
  void tryListGroup() {
    
    List<IInterceptor> interceptors = new ArrayList<>();
    interceptors.add(new Hello());
    interceptors.add(new There());
    
    //DomainBeansPropertyProxy domainBeansProxy = new DomainBeansPropertyProxy();
    //IDomain domain = new Domain(domainBeansProxy);
    //IInterceptorGroup group = new InterceptorList(domain, interceptors, true);
    
    //group.intercept(null);
  }
  
  @Interceptor(1)
  static class Hello implements IInterceptor.IInterceptorInternal {
    @Override
    public String getId() {
      return null;
    }
    
    @Override
    public String getInstanceId() {
      return null;
    }
    
    @Override
    public IDomain getDomain() {
      return null;
    }
    
    @Override
    public IInterceptorOutcome intercept(IInterceptorContext interceptorContext) {
      System.out.println("Hello called");
      interceptorContext.nextIntercept();
      //return "Hello";
      return null;
    }
    
    @Override
    public IInterceptorInternal interceptorInternal() {
      return null;
    }
    
    @Override
    public void run() {
    
    }
    
    @Override
    public Object call() throws Exception {
      return null;
    }
  }
  
  @Interceptor(2)
  static class There implements IInterceptor.IInterceptorInternal {
    
    @Override
    public String getId() {
      return null;
    }
    
    @Override
    public String getInstanceId() {
      return null;
    }
    
    @Override
    public IDomain getDomain() {
      return null;
    }
    
    @Override
    public IInterceptorOutcome intercept(IInterceptorContext interceptorContext) {
      System.out.println("There called");
      interceptorContext.nextIntercept();
      //return "There";
      return null;
    }
    
    @Override
    public IInterceptorInternal interceptorInternal() {
      return this;
    }
    
    @Override
    public void run() {
    
    }
    
    @Override
    public Object call() throws Exception {
      return null;
    }
  }
}
