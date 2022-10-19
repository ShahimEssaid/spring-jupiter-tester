package com.essaid.picocli.commands.unit;

import com.essaid.picocli.commands.support.UnitBase;
import org.junit.jupiter.api.Test;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.Arrays;

public class HelloTest extends UnitBase {
  
  @Test
  void hello(){
  }
  
  @Test
  void environment(){
    System.getenv().keySet().stream().sorted().forEach(s -> System.out.println("Env: "+ s + " Value: "+ System.getenv(s) ));
  }
  
  @Test
  void properties(){
    System.getProperties().keySet().stream().sorted().forEach(o -> System.out.println("Prop: "+ o + " Value: "+ System.getProperty((String) o)));
  }
  
  @Test
  void classPath(){
    String separator = System.getProperty("path.separator");
    Arrays.stream(System.getProperty("java.class.path").split(separator)).sorted().forEach(s -> System.out.println(s));
  }
}
