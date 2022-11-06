package tmp.springboot.example1;

import com.essaid.context.spring.IApplicationDomain;
import com.essaid.context.spring.IFactory;
import com.essaid.context.spring.IScope;
import com.essaid.context.spring.IThreadContext;
import com.essaid.context.spring.Scopes;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import tmp.springboot.example1.comp.ApplicationBeanA;
import tmp.springboot.example1.comp.RequestBeanA;
import tmp.springboot.example1.comp.SessionBeanA;

@SpringBootApplication
public class Boot {
  
  public static void main(String[] args) {
    
    IApplicationDomain domain1 = Scopes.createDomain("Boot 2", true, true, true, true);
    domain1.initialize();
    Scopes.setDomain(domain1, true);
    
    SpringApplication application = new SpringApplication(Boot.class) {
    };
    
    application.addInitializers(new ApplicationContextInitializer<ConfigurableApplicationContext>() {
      @Override
      public void initialize(ConfigurableApplicationContext applicationContext) {
        
        IScope scope = Scopes.createApplicationScope(domain1, applicationContext);
        applicationContext.getBeanFactory().registerScope(scope.getScopeName(), scope);
        
        scope = Scopes.createContainerScope(domain1, applicationContext);
        
        scope = Scopes.createSessionScope(domain1, applicationContext, scope);
        applicationContext.getBeanFactory().registerScope(scope.getScopeName(), scope);
        
        scope = Scopes.createRequestScope(domain1, applicationContext, scope);
        applicationContext.getBeanFactory().registerScope(scope.getScopeName(), scope);
        
        applicationContext.getBeanFactory().registerScope(domain1.getScopeName(), domain1);
        
        applicationContext.addApplicationListener(domain1);
      }
    });
    
    IThreadContext threadContext = domain1.getThreadManager().getThreadContext(true);
    
    ConfigurableApplicationContext context = application.run(args);
    
    IScope session = (IScope) context.getBeanFactory().getRegisteredScope(IFactory.REQUEST_NAME);
    String scopeContextId = session.getScopeContext(domain1.isAutoThreadContext(), domain1.isAutoContext(),
        domain1.isAutoScopeContext()).getScopeContextId();
    System.out.println("======= Request context id:" + scopeContextId);
    
    
    BeanDefinition sessionBeanADefinition = context.getBeanFactory().getBeanDefinition("sessionBeanA");
    String scope = sessionBeanADefinition.getScope();
    
    
    SessionBeanA sessionBeanA = context.getBean(SessionBeanA.class);
    RequestBeanA requestBeanA = context.getBean(RequestBeanA.class);
    ApplicationBeanA applicationBeanA = context.getBean(ApplicationBeanA.class);
    
    context.close();
    domain1.close();
    System.out.println("Done");
    //Arrays.stream(beanDefinitionNames).sorted().forEach(System.out::println);
  }
}
