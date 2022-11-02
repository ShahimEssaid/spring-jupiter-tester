package tmp.springboot.example1;

import com.essaid.context.spring.ISpringScope;
import com.essaid.context.spring.ISpringScopeDomain;
import com.essaid.context.spring.ISpringThreadContext;
import com.essaid.context.spring.SpringScopes;
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
    ISpringScopeDomain domain = SpringScopes.createDomain("example-domain", true, true, true);
    
    SpringScopes.setDomain(domain, false);
    SpringApplication application = new SpringApplication(Boot.class) {
    };
    
    application.addInitializers(new ApplicationContextInitializer<ConfigurableApplicationContext>() {
      @Override
      public void initialize(ConfigurableApplicationContext applicationContext) {
        
        ISpringScope scope = SpringScopes.createSessionScope(domain, applicationContext);
        applicationContext.getBeanFactory().registerScope(scope.getScopeName(), scope);
        
        scope = SpringScopes.createRequestScope(domain, applicationContext);
        applicationContext.getBeanFactory().registerScope(scope.getScopeName(), scope);
        
        scope = SpringScopes.createConversationScope(domain, applicationContext);
        applicationContext.getBeanFactory().registerScope(scope.getScopeName(), scope);
        
        applicationContext.getBeanFactory().registerScope(SpringScopes.APPLICATION_NAME, domain);
        
        applicationContext.addApplicationListener(domain);
        
      }
    });
    
    ISpringThreadContext context1 = domain.getThreadContext();
    
    ConfigurableApplicationContext context = application.run(args);
    Scope session = context.getBeanFactory().getRegisteredScope("session");
    
    BeanDefinition sessionBeanADefinition = context.getBeanFactory().getBeanDefinition("sessionBeanA");
    String scope = sessionBeanADefinition.getScope();
    
    
    SessionBeanA sessionBeanA = context.getBean(SessionBeanA.class);
    RequestBeanA requestBeanA = context.getBean(RequestBeanA.class);
    ApplicationBean applicationBean = context.getBean(ApplicationBean.class);
    
    context.close();
    domain.close();
    System.out.println("Done");
    //Arrays.stream(beanDefinitionNames).sorted().forEach(System.out::println);
  }
}
