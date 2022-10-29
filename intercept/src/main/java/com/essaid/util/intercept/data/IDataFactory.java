package com.essaid.util.intercept.data;

import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.model.IModel;
import com.essaid.util.model.IModelConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface IDataFactory {
  
  default IData createData(IInterceptorContext context,
                   Boolean overridePermissiveness, IModelConfigurer... overrideConfigurers){
    return createData(context, overridePermissiveness, Arrays.asList(overrideConfigurers));
  }
  
  IData createData(IInterceptorContext context,
                    Boolean overridePermissiveness, List<IModelConfigurer> overrideConfigurers);
  
  default IData createNewLocalData(IInterceptorContext context,
                           Boolean overridePermissiveness, IModelConfigurer... overrideConfigurers){
    return createNewLocalData(context, overridePermissiveness, Arrays.asList(overrideConfigurers));
  }
  IData createNewLocalData(IInterceptorContext context,
                            Boolean overrideLocalPermissiveness, List<IModelConfigurer> overrideLocalConfigurers);
  
}
