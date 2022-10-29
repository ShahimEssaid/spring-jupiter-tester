package com.essaid.util.model.defaulthandler;

import com.essaid.util.model.IModel;
import com.essaid.util.model.impl.ModelConfigurer;

public class DefaultMethodConfigurer extends ModelConfigurer {
  
  public DefaultMethodConfigurer() {
    super("id", "name", "vesion", 0, "short", "description", "notes", new Class[]{},
        new Class[]{DefaultMethodHandler.class}, IModel.class);
  }
}
