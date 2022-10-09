package com.essaid.spring.jupiter.tester.jupiter.components;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import org.junit.platform.commons.util.ReflectionUtils;
import org.junit.platform.console.options.CommandLineOptions;
import org.junit.platform.engine.DiscoverySelector;
import org.junit.platform.engine.discovery.ClassNameFilter;
import org.junit.platform.engine.discovery.ClassSelector;
import org.junit.platform.engine.discovery.ClasspathRootSelector;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.LauncherSession;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EventObject;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static org.junit.platform.commons.util.CollectionUtils.toUnmodifiableList;

@Component
public class JupiterRuntime implements ApplicationEventPublisherAware {
  private ApplicationEventPublisher publisher;
  
  public List<DiscoverySelector> getSelectors() {
    return selectors;
  }
  
  
  @Autowired
  public void setSelectors(List<DiscoverySelector> selectors) {
    this.selectors = selectors;
  }
  
  
  private List<DiscoverySelector> selectors;
  
  @EventListener
  public void onEvent(EventObject event) {
    if (event instanceof ContextStartedEvent) {
      publisher.publishEvent(new EventObject("Hello") {
      });
    } else {
      System.out.println("EVENT: " + event);
      System.out.println("Selectors: " + selectors);
    }
  }
  
  @EventListener
  public void onApplicationStart(ApplicationStartedEvent startedEvent) {
    System.out.println("====================   START EVENT  ==============");
    LauncherDiscoveryRequestBuilder discoveryRequestBuilder = LauncherDiscoveryRequestBuilder.request();
    
    
    CommandLineOptions options = new CommandLineOptions();
    
    ClassLoader classLoader = getClass().getClassLoader();
    
    ClassGraph graph = new ClassGraph();

    graph.rejectPackages("org.springframework", "ch.qos.logback", "com.intellij", "io.github.classgraph", "org.apache" +
        ".logging", "org.junit", "sun", "java", "javax", "picocli", "org.yaml", "org.slf4j", "nonapi",  "org" +
        ".aopalliance");
    ScanResult scan = graph.scan();
    List<ClassSelector> collect = scan.getAllClasses().stream().map(classInfo -> {
      classInfo.getPackageName();
      String name = classInfo.getName();
      System.out.println("Class: " + name);
      return DiscoverySelectors.selectClass(classInfo.getName());
      
    }).collect(Collectors.toList());
    
//
//    if (classLoader instanceof URLClassLoader) {
//      URLClassLoader urlClassLoader = (URLClassLoader) classLoader;
//
//
//      selectorList = Arrays.stream(urlClassLoader.getURLs()).map(url -> {
//        try {
//          return DiscoverySelectors.selectUri(url.toURI());
//        } catch (URISyntaxException e) {
//          throw new RuntimeException(e);
//        }
//      }).collect(Collectors.toList());
//
//    } else {
//      Set<Path> rootDirs = new LinkedHashSet<>(ReflectionUtils.getAllClasspathRootDirectories());
//      System.out.println("Root DIRS: " + rootDirs);
//      selectorList = DiscoverySelectors.selectClasspathRoots(rootDirs);
//      ;
//    }
    System.out.println("============== CLASSLOADER: " + classLoader);
    
    Properties properties = System.getProperties();
    SortedSet<Object> keys = new TreeSet<>(properties.keySet());
    keys.forEach(key -> System.out.println(key + " " + properties.get(key)));
    
    discoveryRequestBuilder.selectors(collect);
    discoveryRequestBuilder.filters(ClassNameFilter.includeClassNamePatterns(ClassNameFilter.STANDARD_INCLUDE_PATTERN));
    
    //LauncherDiscoveryRequest discoveryRequest = new DiscoveryRequestCreator().toDiscoveryRequest(options);
    //launcher.execute(discoveryRequest);
    
    SummaryGeneratingListener summaryGeneratingListener = new SummaryGeneratingListener();
    LauncherDiscoveryRequest discoveryRequest = discoveryRequestBuilder.build();
    
    LauncherSession launcherSession = LauncherFactory.openSession();
    Launcher launcher = launcherSession.getLauncher();
    launcher.registerTestExecutionListeners(summaryGeneratingListener);
    
    TestPlan testPlan = launcher.discover(discoveryRequest);
    
    launcher.execute(testPlan);
    
    TestExecutionSummary summary = summaryGeneratingListener.getSummary();
    System.out.println(summary);
    
  }
  
  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    System.out.println(" ============ java.class.path: " + System.getProperty("java.class.path"));
    ClassLoader classLoader = getClass().getClassLoader();
    if (classLoader instanceof URLClassLoader) {
      URLClassLoader urlClassLoader = (URLClassLoader) classLoader;
      Arrays.stream(urlClassLoader.getURLs()).forEach(url -> {
        try {
          System.out.println(url.toURI());
        } catch (URISyntaxException e) {
          throw new RuntimeException(e);
        }
      });
    }
    
    this.publisher = applicationEventPublisher;
  }
}
