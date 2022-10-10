package com.essaid.spring.jupiter.tester.jupiter.components;

import org.junit.platform.engine.DiscoverySelector;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JupiterConfiguration {
  
  @Bean
  DiscoverySelector helloSelector(){
    return DiscoverySelectors.selectClass("Hello");
  }
  
  @Bean
  DiscoverySelector worldSelector(){
    return DiscoverySelectors.selectClass("World");
  }
}
