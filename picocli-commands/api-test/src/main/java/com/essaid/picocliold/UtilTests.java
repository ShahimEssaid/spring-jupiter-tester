package com.essaid.picocliold;

import com.essaid.picocli.commandsold.impl.Util;
import com.essaid.picocliold.support.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class UtilTests extends TestBase {
  
  
  @Test
  void noSubCommands(){
    boolean noSubCommands = Util.isWithoutSubCommands(CommandWithSubcommand.class);
    Assertions.assertEquals(noSubCommands, false);
  }
  
  

}
