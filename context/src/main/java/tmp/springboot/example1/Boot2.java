package tmp.springboot.example1;

import com.essaid.context.spring.ISpringContext;
import com.essaid.context.spring2.IApplicationDomain;
import com.essaid.context.spring2.IScope;
import com.essaid.context.spring2.IThreadContext;
import com.essaid.context.spring2.Scopes;
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
public class Boot2 {
  
  public static void main(String[] args) {
    
    IApplicationDomain domain1 = Scopes.createDomain("Boot 2", true, true, true, true);
    domain1.initialize();
    Scopes.setDomain(domain1, true);
    
    SpringApplication application = new SpringApplication(Boot2.class) {
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
  
    IThreadContext threadContext = domain1.getThreadContext(true);
  
    ConfigurableApplicationContext context = application.run(args);
    
    Scope session = context.getBeanFactory().getRegisteredScope("session");
    
    BeanDefinition sessionBeanADefinition = context.getBeanFactory().getBeanDefinition("sessionBeanA");
    String scope = sessionBeanADefinition.getScope();
    
    
    SessionBeanA sessionBeanA = context.getBean(SessionBeanA.class);
    RequestBeanA requestBeanA = context.getBean(RequestBeanA.class);
    ApplicationBean applicationBean = context.getBean(ApplicationBean.class);
    
    context.close();
    domain1.close();
    System.out.println("Done");
    //Arrays.stream(beanDefinitionNames).sorted().forEach(System.out::println);
  }
}
