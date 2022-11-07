package tmp.springboot.example1.comp;

import com.essaid.context.spring.IContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
@Scope(scopeName = IContainer.REQUEST_NAME, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestBeanA extends StringHolder {
  
  
  @Autowired
  private ApplicationBeanA applicationBeanA;
  
  @Autowired
  private SingletonBeanA singletonBeanA;
  @Autowired
  private SessionBeanA sessionBeanA;
  @Autowired
  private RequestBeanA requestBeanA;
  
  public ApplicationBeanA getApplicationBeanA() {
    return applicationBeanA;
  }
  
  public SingletonBeanA getSingletonBeanA() {
    return singletonBeanA;
  }
  
  public SessionBeanA getSessionBeanA() {
    return sessionBeanA;
  }
  
  public RequestBeanA getRequestBeanA() {
    return requestBeanA;
  }
  
  @PreDestroy
  void preDestroy() {
    System.out.println("=============== in pre destroy RequestBeanA");
  }
}
