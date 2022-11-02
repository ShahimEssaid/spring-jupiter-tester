package com.essaid.context.spring;

public class SpringScopes {
  
  public static final String APPLICATION_NAME = "application";
  public static final int APPLICATION_ORDER = 0;
  public static final String SESSION_NAME = "session";
  public static final int SESSION_ORDER = 1000;
  public static final String CONVERSATION_NAME = "conversation";
  public static final int CONVERSATION_ORDER = 2000;
  public static final String REQUEST_NAME = "request";
  public static final int REQUEST_ORDER = 3000;
  private SpringScopes() {
  }
}
