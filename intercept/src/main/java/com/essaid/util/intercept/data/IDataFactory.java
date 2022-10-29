package com.essaid.util.intercept.data;

import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.model.IModel;
import com.essaid.util.model.IModelConfigurer;

import java.util.List;

public interface IDataFactory {
  
  IData createData(IInterceptorContext context, List<IModelConfigurer> overrideConfigurers,
                    Boolean overridePermissiveness);
  
  IData createNewLocalData(IInterceptorContext context, List<IModelConfigurer> overrideLocalConfigurers,
                            Boolean overrideLocalPermissiveness);
  
}
