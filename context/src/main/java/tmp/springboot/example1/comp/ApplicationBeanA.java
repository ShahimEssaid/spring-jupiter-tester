package tmp.springboot.example1.comp;

import com.essaid.context.spring.IContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Scope(value = IContainer.APPLICATION_NAME, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class ApplicationBeanA extends StringHolder {
  @Autowired
  private ApplicationBeanA applicationBeanA;
  @Autowired
  private SingletonBeanA singletonBeanA;
  @Autowired
  private SessionBeanA sessionBeanA;
  @Autowired
  private RequestBeanA requestBeanA;
  
  public ApplicationBeanA() {
    System.out.println("============= ApplicationBean constructed");
  }
  
  public RequestBeanA getRequestBeanA() {
    return requestBeanA;
  }
  
  public ApplicationBeanA getApplicationBeanA() {
    return applicationBeanA;
  }
  
  public SingletonBeanA getSingletonBeanA() {
    return singletonBeanA;
  }
  
  public SessionBeanA getSessionBeanA() {
    return sessionBeanA;
  }
  
  @PreDestroy
  void preDestroy() {
    System.out.println("=============== in pre destroy ApplicationBean");
  }
}
