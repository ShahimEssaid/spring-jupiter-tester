package com.essaid.util.intercept.config;

import com.essaid.util.intercept.context.DefaultContextFactory;
import com.essaid.util.intercept.data.Data;
import com.essaid.util.intercept.data.DefaultDataFactory;
import com.essaid.util.intercept.data.IData;
import com.essaid.util.intercept.domain.Domain;
import com.essaid.util.intercept.domain.IDomain;
import com.essaid.util.model.IModelConfigurer;
import com.essaid.util.model.beanhandlers.BeanPropertyConfigurer;
import com.essaid.util.model.defaulthandler.DefaultMethodConfigurer;

public class InterceptConfigUtil {
  public static IModelConfigurer[] DEFAULT_CONFIGURERS = new IModelConfigurer[]{new DefaultMethodConfigurer(),
      new BeanPropertyConfigurer(), new InterceptConfigConfigurer()};
  
  public static IDomain createDefaultDomain(boolean isPermisiveDomainData, boolean isPermisiveContextData,
                                            boolean isPermisiveLocalContextData) {
    
    IData domainData = new Data(isPermisiveDomainData);
    domainData.internal().appendConfiguration(DEFAULT_CONFIGURERS);
    
    IDomain domain = new Domain(domainData);
    
    IInterceptConfig interceptConfig = domainData.as(IInterceptConfig.class);
    interceptConfig.setContextDataPermissive(isPermisiveContextData);
    interceptConfig.setContextDataConfigurers(DEFAULT_CONFIGURERS);
    interceptConfig.setContextLocalDataPermissive(isPermisiveLocalContextData);
    interceptConfig.setContextLocalDataConfigurers(DEFAULT_CONFIGURERS);
    
    interceptConfig.setIDataFactory(new DefaultDataFactory(domain));
    interceptConfig.setIContextFactory(new DefaultContextFactory());
    return domain;
  }
}
