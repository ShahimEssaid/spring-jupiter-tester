package com.essaid.util.intercept.context;

import com.essaid.util.intercept.domain.IDomain;
import com.essaid.util.intercept.interceptor.IInterceptor;
import com.essaid.util.model.IModel;

public interface IContextFactory {
  IInterceptorContext createContext(IDomain domain, IModel data, IModel localData,
                                    IInterceptor interceptor);
}
