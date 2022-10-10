package com.essaid.spring.jupiter.tester.temp;

import ch.qos.logback.classic.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.logging.LogLevel;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;

@SpringBootApplication
public class BootTraceLog {
  
  public static void main(String[] args) {
    SpringApplication application = new SpringApplication(BootTraceLog.class);
//    {
//      @Override
//      protected void configureEnvironment(ConfigurableEnvironment environment, String[] args) {
//        super.configureEnvironment(environment, args);
//        MutablePropertySources propertySources = environment.getPropertySources();
//        MapPropertySource mapPropertySource = new MapPropertySource("shahim", new HashMap<>());
//        mapPropertySource.getSource().put("logging.level", LogLevel.TRACE);
//        propertySources.addFirst(mapPropertySource);
//      }
//    };
  
    ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    logger.setLevel(Level.TRACE);
    ConfigurableApplicationContext run = application.run(args);
    run.start();
    run.stop();
  
  
  }
}
