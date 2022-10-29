package com.essaid.util.intercept.interceptor.impl;

import com.essaid.util.intercept.config.IInterceptConfig;
import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.intercept.data.IData;
import com.essaid.util.intercept.domain.IDomain;

import com.essaid.util.intercept.interceptor.IInterceptor;
import com.essaid.util.model.IModel;
import com.essaid.util.model.IModelConfigurer;
import com.essaid.util.intercept.interceptor.IInterceptorOutcome;

import java.util.List;

public class AbstractInterceptorGroup implements IInterceptor.IInterceptorInternal {
  
  private final IDomain domain;
  private final List<IModelConfigurer> dataConfigurerOverrides;
  private final List<IModelConfigurer> localDataConfigurerOverrides;
  private final Boolean overridePermissiveness;
  private final Boolean overrideLocalPermissiveness;
  
  public AbstractInterceptorGroup(IDomain domain, List<IModelConfigurer> dataConfigurerOverrides,
                                  Boolean overridePermissiveness, List<IModelConfigurer> localDataConfigurerOverrides
      , Boolean overrideLocalPermissiveness) {
    this.domain = domain;
    this.dataConfigurerOverrides = dataConfigurerOverrides;
    this.localDataConfigurerOverrides = localDataConfigurerOverrides;
    this.overridePermissiveness = overridePermissiveness;
    this.overrideLocalPermissiveness = overrideLocalPermissiveness;
  }
  
  @Override
  final public IInterceptorOutcome intercept(IInterceptorContext interceptorContext) {
    interceptorContext = buildContext(interceptorContext);
    return interceptorContext.nextIntercept();
  }
  
  protected IInterceptorContext buildContext(IInterceptorContext interceptorContext) {
    IInterceptConfig factories = getDomain().as(IInterceptConfig.class);
    IData data = factories.getIDataFactory()
        .createData(interceptorContext, overridePermissiveness, dataConfigurerOverrides);
    IData localData = factories.getIDataFactory()
        .createNewLocalData(interceptorContext, overrideLocalPermissiveness, localDataConfigurerOverrides);
    
    return factories.getIContextFactory().createContext(domain, data, localData, this);
  }
  
  @Override
  final public IDomain getDomain() {
    return domain;
  }
  
  @Override
  public void run() {
    intercept(null);
  }
  
  @Override
  public Object call() throws Exception {
    return intercept(null);
  }
  
  @Override
  public String getId() {
    return null;
  }
  
  @Override
  public String getInstanceId() {
    return null;
  }
}
