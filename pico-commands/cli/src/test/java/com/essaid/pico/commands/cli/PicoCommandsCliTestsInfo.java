package com.essaid.pico.commands.cli;

import com.essaid.pico.commands.impl.NoOpCommandType;
import com.essaid.pico.commands.PicoCommandType;
import com.essaid.pico.commands.PicoCommandInstance;
import com.essaid.pico.commands.PicoCommands;
import com.essaid.pico.commands.PicoCommandsLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PicoCommandsCliTestsInfo {
  
  private PicoCommands picoCommands;
  private PicoCommandType noOpCommand;
  private PicoCommandInstance noOpCommandInstance;
  
  @Test
  @Order(1)
  void picoCommandsExits(){
    this.picoCommands = PicoCommandsLoader.loadCommands(getClass().getClassLoader());
    Assertions.assertNotNull(picoCommands);
  }
  
  @Test
  @Order(2)
  void noOpCommandExists(){
    this.noOpCommand = picoCommands.getCommandByPath(NoOpCommandType.NO_OP_COMMAND_NAME);
    Assertions.assertNotNull(noOpCommand);
  }
  


}
