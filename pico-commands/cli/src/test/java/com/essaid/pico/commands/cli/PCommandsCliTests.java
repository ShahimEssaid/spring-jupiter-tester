package com.essaid.pico.commands.cli;

import com.essaid.pico.commands.PCommand;
import com.essaid.pico.commands.impl.NoOpPCommandType;
import com.essaid.pico.commands.PCommandType;
import com.essaid.pico.commands.PCommands;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PCommandsCliTests {
  
  private PCommands pCommands;
  private PCommandType noOpCommandFactory;
  private PCommandType demoCommandFactory;
  private PCommand demoCommand;
  
  @Test
  @Order(1)
  void picoCommandsExits() {
    this.pCommands = PCommands.loadCommands(getClass().getClassLoader());
    Assertions.assertNotNull(pCommands);
  }
  
  @Test
  @Order(2)
  void noOpCommandTypeExists() {
    this.noOpCommandFactory = pCommands.getCommandTypeByPath(NoOpPCommandType.NO_OP_COMMAND_NAME);
    Assertions.assertNotNull(noOpCommandFactory);
  }
  
  @Test
  @Order(2)
  void noDemoCommandTypeExists() {
    this.demoCommandFactory = pCommands.getCommandTypeByName(DemoCommandType.DEMO_COMMAND_NAME);
    Assertions.assertNotNull(noOpCommandFactory);
  }
  
  
  @Test
  @Order(3)
  void demoCommandExists() {
    this.demoCommand = demoCommandFactory.createCommand();
    Assertions.assertNotNull(demoCommand);
  }
  
  @Test
  @Order(4)
  void runDemoCommand() {
    int execute = demoCommand.getCommandLine().execute(new String[]{});
    Assertions.assertEquals(execute, 0);
    assert demoCommand.getContext().get(PCommands.PICO_COMMAND_OUTPUT).equals(DemoCommandType.DEMO_COMMAND_NAME);
  }
  
  
}
