package com.essaid.util.intercept.data;

import com.essaid.util.intercept.config.IInterceptConfig;
import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.intercept.domain.IDomain;
import com.essaid.util.model.IModelConfigurer;

import java.util.List;

public class DefaultDataFactory implements IDataFactory {

  private final IDomain domain;
  private final IInterceptConfig interceptConfig;
  
  public DefaultDataFactory(IDomain domain) {
    this.domain = domain;
    this.interceptConfig = domain.modelableAs(IInterceptConfig.class);
  }

  
  @Override
  public IData createData(IInterceptorContext context,
                           Boolean permissiveDataOverride, List<IModelConfigurer> configurersOverride) {
    if (context != null) {
      IData data = context.internal().getData();
      data.internal().prependConfiguration(configurersOverride);
      return data;
    } else {
      IData data = new Data(permissiveDataOverride != null ? permissiveDataOverride : interceptConfig.isContextDataPermissive());
      List<IModelConfigurer> contextDataConfigurers = interceptConfig.getContextDataConfigurers();
      data.internal().appendConfiguration(contextDataConfigurers);
      data.internal().prependConfiguration(configurersOverride);
      return data;
    }
  }
  
  @Override
  public IData createNewLocalData(IInterceptorContext context,
                                   Boolean permissiveLocalDataOverride, List<IModelConfigurer> configurersOverride ) {
  
    IData localData = new Data(permissiveLocalDataOverride != null ? permissiveLocalDataOverride : interceptConfig.isContextLocalDataPermissive());
    localData.internal().appendConfiguration(interceptConfig.getContextLocalDataConfigurers());
    localData.internal().prependConfiguration(configurersOverride);
    
    return localData;
  }
}
