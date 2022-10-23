package com.essaid.util.intercept.group;

import com.essaid.util.asserts.Asserts;
import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.intercept.data.IInterceptorContextGlobalData;
import com.essaid.util.intercept.data.IInterceptorContextLocalData;
import com.essaid.util.intercept.data.IInterceptorGroupData;
import com.essaid.util.intercept.data.InterceptorContextGlobalData;
import com.essaid.util.intercept.data.InterceptorGroupData;
import com.essaid.util.intercept.data.InterceptorLocalContextData;
import com.essaid.util.intercept.domain.IDomain;

public abstract class AbstractInterceptorGroup<D extends IDomain, R extends Object,
    C extends IInterceptorContext<D, R, C>> implements IInterceptorGroup<D, R, C> {
  
  private final IInterceptorGroupData groupData = new InterceptorGroupData();
  private final D domain;
  
  public AbstractInterceptorGroup(D domain) {
    this.domain = domain;
  }
  
  @Override
  final public R doInterceptor(IInterceptorContext<D, R, C> interceptorContext) {
    IInterceptorContextGlobalData globalData = doGetGlobalData(interceptorContext);
    IInterceptorContextLocalData localData = doGetLocalData(interceptorContext);
    
    IInterceptorContext<D, R, C> context = doBuildInterceptorContext(this, globalData, localData);
    R r = context.doNextInterceptor();
  
    return r;
  }
  
  abstract protected IInterceptorContext<D, R, C> doBuildInterceptorContext(AbstractInterceptorGroup<D, R, C> drcAbstractInterceptorGroup, IInterceptorContextGlobalData globalData, IInterceptorContextLocalData localData);
  
  protected IInterceptorContextLocalData doGetLocalData(IInterceptorContext<D, R, C> interceptorContext) {
    return new InterceptorLocalContextData();
  }
  
  protected IInterceptorContextGlobalData doGetGlobalData(IInterceptorContext<D, R, C> interceptorContext) {
    return interceptorContext != null? interceptorContext.getGlobalData() : new InterceptorContextGlobalData();
  }
  
  @Override
  final public IInterceptorGroupData getGroupData() {
    return groupData;
  }
  
  @Override
  final public D getDomain() {
    return domain;
  }

}
