package com.essaid.util.intercept.context;

import com.essaid.util.intercept.domain.IDomain;
import com.essaid.util.intercept.data.IData;
import com.essaid.util.intercept.interceptor.IInterceptorOutcome;
import com.essaid.util.model.IModelInterface;

/**
 * TODO: implement global data as a thread local
 */
public interface IInterceptorContext {
  
  IInterceptorOutcome nextIntercept();
  
  IDomain getDomain();
  
  <I extends IModelInterface> I getDataAs(Class<I> cls);
  
  
  default IInterceptorContextInternal internal() {
    return (IInterceptorContextInternal) this;
  }
  
  interface IInterceptorContextInternal extends IInterceptorContext {
    
    IData getData();
  
    IData getLocalData();
    
    <I extends IModelInterface> I getLocalDataAs(Class<I> cls);
    
  }
  
}
