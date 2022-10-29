package com.essaid.util.intercept.domain;

import com.essaid.util.intercept.config.IInterceptConfig;
import com.essaid.util.intercept.config.InterceptConfigConfigurer;
import com.essaid.util.intercept.context.DefaultContextFactory;
import com.essaid.util.intercept.data.Data;
import com.essaid.util.intercept.data.DefaultDataFactory;
import com.essaid.util.model.IModel;
import com.essaid.util.model.IModelInterface;

public class Domain implements IDomain {
  
  
  private final IModel model;
  
  public Domain(IModel domainModel, boolean isContextDataPermissive, boolean isContextLocalDataPermissive) {
    this.model = domainModel;
    this.model.internal().appendConfiguration(new InterceptConfigConfigurer());
    IInterceptConfig config = this.model.as(IInterceptConfig.class);
    
    config.setContextDataConfigurers();
    config.setContextLocalDataConfigurers();
    config.setIContextFactory(new DefaultContextFactory(this));
    config.setIDataFactory(new DefaultDataFactory(this));
    config.setContextDataPermissive(isContextDataPermissive);
    config.setContextLocalDataPermissive(isContextLocalDataPermissive);
  }
  
  public Domain() {
    this(new Data(), false, false);
  }
  
  
  @Override
  public <I extends IModelInterface> I as(Class<I> cls) {
    return model.as(cls);
  }
  
  @Override
  public IModel getModel() {
    return model;
  }
}
