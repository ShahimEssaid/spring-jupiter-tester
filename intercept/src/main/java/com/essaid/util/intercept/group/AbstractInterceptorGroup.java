package com.essaid.util.intercept.group;

import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.intercept.data.IInterceptorContextGlobalData;
import com.essaid.util.intercept.data.IInterceptorContextLocalData;
import com.essaid.util.intercept.data.IInterceptorGroupData;
import com.essaid.util.intercept.data.InterceptorContextGlobalData;
import com.essaid.util.intercept.data.InterceptorGroupData;
import com.essaid.util.intercept.data.InterceptorLocalContextData;
import com.essaid.util.intercept.domain.Domain;

public abstract class AbstractInterceptorGroup<D extends Domain> implements IInterceptorGroup<D> {
  
  private final IInterceptorGroupData groupData = new InterceptorGroupData();
  private final Domain domain;
  
  public AbstractInterceptorGroup(Domain domain) {
    this.domain = domain;
  }
  
  @Override
  final public Object doInterceptor(IInterceptorContext interceptorContext) {
    IInterceptorContextGlobalData globalData = doGetGlobalData(interceptorContext);
    IInterceptorContextLocalData localData = doGetLocalData(interceptorContext);
    
    IInterceptorContext context = doBuildInterceptorContext(this, globalData, localData);
    Object r = context.doNextInterceptor();
  
    return r;
  }
  
  abstract protected IInterceptorContext doBuildInterceptorContext(AbstractInterceptorGroup<D> drcAbstractInterceptorGroup, IInterceptorContextGlobalData globalData, IInterceptorContextLocalData localData);
  
  protected IInterceptorContextLocalData doGetLocalData(IInterceptorContext interceptorContext) {
    return new InterceptorLocalContextData();
  }
  
  protected IInterceptorContextGlobalData doGetGlobalData(IInterceptorContext interceptorContext) {
    return interceptorContext != null? interceptorContext.getGlobalData() : new InterceptorContextGlobalData();
  }
  
  @Override
  final public IInterceptorGroupData getGroupData() {
    return groupData;
  }
  
  @Override
  final public Domain getDomain() {
    return domain;
  }

}
