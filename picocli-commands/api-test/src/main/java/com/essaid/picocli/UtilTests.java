package com.essaid.picocli;

import com.essaid.picocli.commands.impl.Util;
import com.essaid.picocli.support.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class UtilTests extends TestBase {
  
  
  @Test
  void noSubCommands(){
    boolean noSubCommands = Util.isWithoutSubCommands(CommandWithSubcommand.class);
    Assertions.assertEquals(noSubCommands, false);
  }
  
  

}
