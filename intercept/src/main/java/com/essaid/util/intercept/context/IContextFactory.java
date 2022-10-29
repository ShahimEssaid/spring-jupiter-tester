package com.essaid.util.intercept.context;

import com.essaid.util.intercept.data.IData;
import com.essaid.util.intercept.domain.IDomain;
import com.essaid.util.intercept.interceptor.IInterceptor;
import com.essaid.util.model.IModel;

public interface IContextFactory {
  IInterceptorContext createContext(IDomain domain, IData data, IData localData,
                                    IInterceptor interceptor);
}
