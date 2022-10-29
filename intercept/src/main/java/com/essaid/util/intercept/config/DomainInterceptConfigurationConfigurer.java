package com.essaid.util.intercept.config;

import com.essaid.util.intercept.data.IData;
import com.essaid.util.intercept.interceptor.impl.IInterceptFactories;
import com.essaid.util.model.impl.ModelConfigurer;

public class DomainInterceptConfigurationConfigurer extends ModelConfigurer {
  public DomainInterceptConfigurationConfigurer() {
    super("id", "name", "version", 0, "shortDescription", "description", "notes",
        new Class[]{IDomainInterceptConfigurationInterface.class, IInterceptFactories.class}, new Class[]{},
        IData.class);
  }
}
