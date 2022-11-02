package tmp.springboot.example1;

import com.essaid.context.spring.ISpringContext;
import com.essaid.context.spring.ISpringScope;
import com.essaid.context.spring.SpringContextDomain;
import com.essaid.context.spring.SpringScopes;
import com.essaid.context.spring.SpringThreadManager;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.Scope;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import tmp.springboot.example1.comp.ApplicationBean;
import tmp.springboot.example1.comp.RequestBeanA;
import tmp.springboot.example1.comp.SessionBeanA;

@SpringBootApplication
public class Boot {
  
  public static void main(String[] args) {
    SpringContextDomain domain = new SpringContextDomain();
    domain.setAutoCreateContext(true);
    domain.setAutoCreateScopeData(true);
    SpringThreadManager.setDomain(domain);
    SpringApplication application = new SpringApplication(Boot.class) {
    };
    
    application.addInitializers(new ApplicationContextInitializer<ConfigurableApplicationContext>() {
      @Override
      public void initialize(ConfigurableApplicationContext applicationContext) {
  
  
        ISpringScope scope = domain.createSessionScope(applicationContext);
        applicationContext.getBeanFactory().registerScope(scope.getScopeName(), scope);
        
        scope = domain.createRequestScope(applicationContext);
        applicationContext.getBeanFactory().registerScope(scope.getScopeName(), scope);
        
        scope = domain.createConversationScope(applicationContext);
        applicationContext.getBeanFactory().registerScope(scope.getScopeName(), scope);
        
        applicationContext.getBeanFactory().registerScope(SpringScopes.APPLICATION_NAME, domain);
        
        applicationContext.addApplicationListener(domain);
      }
    });
    
    ISpringContext context1 = SpringThreadManager.getContext();
    
    ConfigurableApplicationContext context = application.run(args);
    Scope session = context.getBeanFactory().getRegisteredScope("session");
    
    BeanDefinition sessionBeanADefinition = context.getBeanFactory().getBeanDefinition("sessionBeanA");
    String scope = sessionBeanADefinition.getScope();
    
    //String[] beanDefinitionNames = context.getBeanDefinitionNames();
//    ISpringContext context2 = domain.createContext(Thread.currentThread());
//    Scope sessionScope = context.getBeanFactory().getRegisteredScope(SpringScopes.SESSION_NAME);
//    context2.setScopeData((ISpringScope) sessionScope, domain.createScopeData((ISpringScope) sessionScope, context2, null));
//    SpringThreadManager.setContext(context2);
    
    
    SessionBeanA sessionBeanA = context.getBean(SessionBeanA.class);
    RequestBeanA requestBeanA = context.getBean(RequestBeanA.class);
    ApplicationBean applicationBean = context.getBean(ApplicationBean.class);
    
    context.close();
    domain.close();
    System.out.println("Done");
    //Arrays.stream(beanDefinitionNames).sorted().forEach(System.out::println);
  }
}
