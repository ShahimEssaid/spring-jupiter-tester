package com.essaid.context.spring.scope;

import com.essaid.context.spring.SpringScope;
import com.essaid.context.spring.SpringScopes;
import org.springframework.context.ConfigurableApplicationContext;

public class SessionScope extends SpringScope {
  
  public SessionScope(ConfigurableApplicationContext context){
    super(SpringScopes.SESSION_NAME, SpringScopes.SESSION_ORDER, context);
  }
}
