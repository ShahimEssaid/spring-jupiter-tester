package com.essaid.util.intercept.context;

import com.essaid.util.asserts.Asserts;
import com.essaid.util.intercept.data.IData;
import com.essaid.util.intercept.domain.IDomain;
import com.essaid.util.intercept.interceptor.IInterceptor;
import com.essaid.util.intercept.interceptor.IInterceptorOutcome;
import com.essaid.util.model.IModelInterface;
import com.essaid.util.model.IModel;

abstract  public class AbstractInterceptorContext implements IInterceptorContext.IInterceptorContextInternal {
  
  private final IDomain domain;
  private final IData data;
  private final IData localData;
  
  public AbstractInterceptorContext(IDomain domain, IData data, IData localData) {
    Asserts.notNull(domain, "IDomain can't be null while constructing AbstractInterceptorContext");
    Asserts.notNull(data, "IModelProxy interceptor data can't be null while constructing " +
        "AbstractInterceptorContext");
    Asserts.notNull(localData, "IModelProxy interceptor local data can't be null while constructing " +
        "AbstractInterceptorContext");
    
    this.domain = domain;
    this.data = data;
    this.localData = localData;
  }
  
  @Override
  public IData getData() {
    return data;
  }
  
  @Override
  public IData getLocalData() {
    return localData;
  }
  
  @Override
  public <I extends IModelInterface> I getLocalDataAs(Class<I> cls) {
    return localData.as(cls);
  }
  
  @Override
  public IInterceptorOutcome nextIntercept() {
    IInterceptor nextInterceptor = getNextInterceptor();
    IInterceptorOutcome outcome = null;
    if (nextInterceptor != null) {
      outcome = nextInterceptor.intercept(this);
    }
    return outcome;
  }
  
  @Override
  public IDomain getDomain() {
    return domain;
  }
  
  @Override
  public <I extends IModelInterface> I getDataAs(Class<I> cls) {
    return data.as(cls);
  }
  
  abstract protected IInterceptor getNextInterceptor();
}
