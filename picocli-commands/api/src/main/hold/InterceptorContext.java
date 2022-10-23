package com.essaid.util.intercept;

import com.essaid.util.asserts.Asserts;

import java.util.Iterator;
import java.util.List;

public class InterceptorContext implements IInterceptorContext {
  
  private final List<IInterceptor> interceptors;
  private final IInterceptorData data;
  private final IInterceptorGroup chain;
  private final Iterator<IInterceptor> iterator;
  
  /**
   * @param chain
   * @param interceptors a list of interceptors. The assumption is that the list is not null and is immutable.
   * @param data
   */
  public InterceptorContext(IInterceptorGroup chain, List<IInterceptor> interceptors, IInterceptorData data) {
    this.chain = Asserts.notNull(chain, "IInterceptorChain can't be null while constructing InterceptorContext.");
    this.data = Asserts.notNull(data, "IInterceptorData can't be null while constructing InterceptorContext.");
    this.interceptors = Asserts.notNull(interceptors, "List<IInterceptor> can't be null while constructing " +
        "InterceptorContext.");
    this.iterator = this.interceptors.iterator();
  }
  
  @Override
  final public IInterceptorGroup getInterceptorGroup() {
    return chain;
  }
  
  @Override
  final public List<IInterceptor> getInterceptors() {
    return interceptors;
  }
  
  @Override
  final public <D extends IInterceptorData> D getData(Class<D> dataClass) {
    return data.adapt(dataClass);
  }
  
  @Override
  final public Object doNextInterceptor() {
    IInterceptor nextInterceptor = getNextInterceptor();
    return nextInterceptor == null ? null : nextInterceptor.doInterceptor(this);
  }
  
  protected IInterceptor getNextInterceptor() {
    return iterator.hasNext() ? iterator.next() : null;
  }
  
}
