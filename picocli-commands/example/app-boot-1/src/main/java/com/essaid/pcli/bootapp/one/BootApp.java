package com.essaid.pcli.bootapp.one;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

public class BootApp extends SpringApplication {
  public BootApp(Class<?>... primarySources) {
    super(primarySources);
  }
  
  public BootApp(ResourceLoader resourceLoader, Class<?>... primarySources) {
    super(resourceLoader, primarySources);
  }
  
  @Override
  protected void configureEnvironment(ConfigurableEnvironment environment, String[] args) {
    
    Map<String, Object> map = new HashMap<>();
    map.put("com.essaid.picocli.commands.commands_name", "shahim-commands");
    environment.getPropertySources().addLast(new MapPropertySource("shahim", map));
    super.configureEnvironment(environment, args);
  }
}
