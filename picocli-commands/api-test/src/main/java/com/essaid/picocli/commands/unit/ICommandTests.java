package com.essaid.picocli.commands.unit;

import com.essaid.picocli.commands.CommandLineConfig;
import com.essaid.picocli.commands.ICommands;
import com.essaid.picocli.commands.command.SetFieldHello;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ICommandTests {
  
  @Test
  void setHelloField() {
    ICommands commands = ICommands.getDefaultInstance();
    commands.addCommandType(null, "test.setField", SetFieldHello.class, null, "test " + "example");
    
    CommandLineConfig config = commands.getDefaultCommandLineConfig();
    
    int execute = config.getCommandLine().execute(new String[]{"set-field-hello", "--fieldValue", "hello"});
    
    SetFieldHello command = config.getSubCommands().get(0).getCommandLine().getCommand();
    assertThat(command.getField()).isEqualTo("hello");
    
  }
}
