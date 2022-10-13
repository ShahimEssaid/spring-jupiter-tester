package com.essaid.picocli.commands2.impl;

import com.essaid.picocli.commands2.PCommandType2;
import com.essaid.picocli.commands2.PCommands2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import picocli.CommandLine;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DemoEmptyConstructorTest {
  
  private PCommands2 pCommands2;
  
  @Test
  @Order(1)
  void commands2HasDemoCommandType(){
    this.pCommands2 = PCommands2.loadCommands();
    Assertions.assertNotNull(pCommands2);
    PCommandType2 commandTypeByName = pCommands2.getCommandTypeByName(DemoEmptyConstructor.NAME);
    Assertions.assertNotNull(commandTypeByName);
    
    CommandLine commandLine = commandTypeByName.createCommandLine();
    Assertions.assertNotNull(commandLine);
  }
  
//  @Test
//  @Order(2)
//  void commands2HasDemoCommandType(){
//    this.pCommands2 = PCommands2.loadCommands();
//    Assertions.assertNotNull(pCommands2);
//  }
}
