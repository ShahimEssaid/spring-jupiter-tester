package com.essaid.util.intercept.test;

import com.essaid.util.intercept.config.InterceptConfigConfigurer;
import com.essaid.util.intercept.config.IInterceptConfig;
import com.essaid.util.intercept.config.InterceptConfigUtil;
import com.essaid.util.intercept.context.DefaultContextFactory;
import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.intercept.data.Data;
import com.essaid.util.intercept.data.DefaultDataFactory;
import com.essaid.util.intercept.data.IData;
import com.essaid.util.intercept.domain.Domain;
import com.essaid.util.intercept.domain.IDomain;
//import com.essaid.util.intercept.domain.impl.DomainBeansPropertyProxy;
import com.essaid.util.intercept.interceptor.IInterceptor;
import com.essaid.util.intercept.interceptor.IInterceptorOutcome;
import com.essaid.util.intercept.interceptor.Interceptor;
import com.essaid.util.intercept.interceptor.impl.InterceptorList;
import com.essaid.util.model.IModelConfigurer;
import com.essaid.util.model.IModelInterface;
import com.essaid.util.model.beanhandlers.BeanPropertyConfigurer;
import com.essaid.util.model.defaulthandler.DefaultMethodConfigurer;
import com.essaid.util.model.impl.ModelConfigurer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class MiscTests {
  
  @Test
  void tryListGroup() {
    
    List<IInterceptor> interceptors = new ArrayList<>();
    interceptors.add(new Hello());
    interceptors.add(new There());
  
    IDomain defaultDomain = InterceptConfigUtil.createDefaultDomain(false, false, false);
    IInterceptConfig as = defaultDomain.as(IInterceptConfig.class);
    List<IModelConfigurer> contextDataConfigurers = as.getContextDataConfigurers();
    
    as.getContextDataConfigurers().add(new HelloDataConfigurer());
    as.getContextLocalDataConfigurers().add(new HelloDataConfigurer());
  
  
    InterceptorList interceptorList = new InterceptorList(defaultDomain, null, false, null, false, true);
    interceptorList.addInterceptors(interceptors);
    IInterceptorOutcome intercept = interceptorList.intercept(null);
  }
  
  interface  HelloData extends IModelInterface{
    void setHello(String hello);
    String getHello();
  }
  
  class HelloDataConfigurer extends ModelConfigurer{
  
    public HelloDataConfigurer() {
      super("id", "name", "version", 0, "shortDescription", "description", "notes", new Class[]{HelloData.class}, new Class[]{},
          IData.class);
    }
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
      HelloData helloData = interceptorContext.getDataAs(HelloData.class);
      helloData.setHello("Hello called");
      System.out.println(helloData.getHello());
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
