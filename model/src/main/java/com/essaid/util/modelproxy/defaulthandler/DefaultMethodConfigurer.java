package com.essaid.util.modelproxy.defaulthandler;

import com.essaid.util.modelproxy.IModel;
import com.essaid.util.modelproxy.impl.ModelConfigurer;

public class DefaultMethodConfigurer extends ModelConfigurer {
  
  public DefaultMethodConfigurer() {
    super("id", "name", "vesion", 0, "short", "description", "notes", new Class[]{},
        new Class[]{DefaultMethodHandler.class}, IModel.class);
  }
}
