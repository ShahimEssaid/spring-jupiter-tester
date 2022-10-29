package com.essaid.util.intercept.test;

import com.essaid.util.intercept.config.IInterceptConfig;
import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.intercept.data.IData;
import com.essaid.util.intercept.domain.Domain;
import com.essaid.util.intercept.domain.IDomain;
//import com.essaid.util.intercept.domain.impl.DomainBeansPropertyProxy;
import com.essaid.util.intercept.interceptor.IInterceptor;
import com.essaid.util.intercept.interceptor.IInterceptorGroup;
import com.essaid.util.intercept.interceptor.IInterceptorOutcome;
import com.essaid.util.intercept.interceptor.Interceptor;
import com.essaid.util.intercept.interceptor.impl.InterceptorList;
import com.essaid.util.model.IModelInterface;
import com.essaid.util.model.impl.ModelConfigurer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MiscTests {
  
  @Test
  void tryListGroup() {
    

  
    IDomain defaultDomain = new Domain();
    IInterceptConfig config = defaultDomain.modelableAs(IInterceptConfig.class);
    
    config.getContextDataConfigurers().add(new HelloDataConfigurer());
    config.getContextLocalDataConfigurers().add(new HelloDataConfigurer());
    
    InterceptorList interceptorList = new InterceptorList(defaultDomain, null, false, null, false, true);
  
    List<IInterceptor> interceptors = new ArrayList<>();
    interceptors.add(new StringHolder(interceptorList, "Hello"));
    interceptors.add(new StringHolder(interceptorList, "There"));

    
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
  static class StringHolder implements IInterceptor.IInterceptorInternal {
  
    private final IInterceptorGroup group;
  
    public String getString() {
      return string;
    }
  
    private final String string;
  
    StringHolder(IInterceptorGroup group, String string){
      this.group = group;
      this.string = string;
    }
    @Override
    public String getId() {
      return null;
    }
    
    @Override
    public String getInstanceId() {
      return null;
    }
  
    @Override
    public IInterceptorGroup getInterceptorGroup() {
      return group;
    }
  
    @Override
    public IDomain getDomain() {
      return null;
    }
    
    @Override
    public IInterceptorOutcome intercept(IInterceptorContext interceptorContext) {
      System.out.println("Interceptor with string: "+ getString() + ", starting.");
      HelloData helloData = interceptorContext.getDataAs(HelloData.class);
      helloData.setHello("Hello called");
      System.out.println(helloData.getHello());
      interceptorContext.nextIntercept();
      //return "Hello";
      System.out.println("Interceptor with string: "+ getString() + ", ending.");
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
  
}
