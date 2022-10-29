package com.essaid.util.intercept.hold;

import com.essaid.util.intercept.interceptor.IInterceptor;

/**
 * This returns a new instance of the interceptor each time it is called.
 */
public interface IInterceptorFactory {
  /**
   * Fully qualified ID of the factory.
   * @return
   */
  String getInterceptorId();
  
  boolean isInterceptorGroup();
  
  IInterceptor createInterceptor(String instanceId);
}
