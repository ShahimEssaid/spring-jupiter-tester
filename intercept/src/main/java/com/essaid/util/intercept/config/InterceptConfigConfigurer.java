package com.essaid.util.intercept.config;

import com.essaid.util.intercept.data.IData;
import com.essaid.util.model.impl.ModelConfigurer;

public class InterceptConfigConfigurer extends ModelConfigurer {
  public InterceptConfigConfigurer() {
    super("id", "name", "version", 0, "shortDescription", "description", "notes",
        new Class[]{IInterceptConfig.class}, new Class[]{},
        IData.class);
  }
}
