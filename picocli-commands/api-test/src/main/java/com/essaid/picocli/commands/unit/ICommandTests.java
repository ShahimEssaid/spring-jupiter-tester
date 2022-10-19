package com.essaid.picocli.commands.unit;

import com.essaid.picocli.commands.ClasspathCommandFactory;
import com.essaid.picocli.commands.CommandLineConfig;
import com.essaid.picocli.commands.ICommands2;
import com.essaid.picocli.commands.command.SetFieldHello;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ICommandTests {
  
  @Test
  void setHelloField() {
    ICommands2 commands = ICommands2.getDefaultInstance();
    commands.addCommandType(null, "test.setField", new ClasspathCommandFactory(SetFieldHello.class, null), "test " +
        "example");
    
    CommandLineConfig config = commands.getDefaultCommandLineConfig();
  
    int execute = config.getCommandLine().execute(new String[]{"set-field-hello", "--fieldValue", "hello"});
  
    SetFieldHello command = config.getSubCommands().get(0).getCommandLine().getCommand();
    assertThat(command.getField()).isEqualTo("hello");
    
  }
}
