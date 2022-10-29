package com.essaid.util.intercept.data;

import com.essaid.util.intercept.context.IInterceptorContext;
import com.essaid.util.model.IModel;
import com.essaid.util.model.IModelConfigurer;
import com.essaid.util.model.impl.Model;

import java.util.List;

public class DefaultDataFactory implements IDataFactory {
  
  private final boolean permissive;
  private final List<IModelConfigurer> configurerList;
  private final List<IModelConfigurer> localConfigurerList;
  private final boolean localInterfacePermissive;
  
  public DefaultDataFactory(List<IModelConfigurer> configurerList, boolean interfacePermissive,
                            List<IModelConfigurer> localConfigurerList, boolean localInterfacePermissive) {
    this.configurerList = configurerList;
    this.permissive = interfacePermissive;
    
    this.localConfigurerList = localConfigurerList;
    this.localInterfacePermissive = localInterfacePermissive;
  }
//
//  public DefaultDataFactory(){
//    this.configurerList = null;
//    this.permissive = false;
//    this.localConfigurerList = null;
//    this.localInterfacePermissive = false;
//  }
  
  @Override
  public IData createData(IInterceptorContext context, List<IModelConfigurer> overrideConfigurers,
                           Boolean permissiveData) {
    if (context != null) {
      IData data = context.internal().getData();
      data.internal().prependConfiguration(overrideConfigurers);
      return data;
    } else {
      IData data = new Data(permissiveData != null ? permissiveData : permissive);
      data.internal().appendConfiguration(configurerList);
      data.internal().prependConfiguration(overrideConfigurers);
      return data;
    }
  }
  
  @Override
  public IData createNewLocalData(IInterceptorContext context, List<IModelConfigurer> overrideConfigurers,
                                   Boolean permissiveData) {
  
    IData localData = new Data(permissiveData != null ? permissiveData : localInterfacePermissive);
    localData.internal().appendConfiguration(localConfigurerList);
    localData.internal().prependConfiguration(overrideConfigurers);
    
    return localData;
  }
}
