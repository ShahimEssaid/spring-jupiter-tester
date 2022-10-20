package com.essaid.picocli.commands.command;

import com.essaid.picocli.commands.Command;
import com.essaid.picocli.commands.picocli.CommandLine;

@CommandLine.Command(name = "set-field-hello")
public class SetFieldHello extends Command {
  
  SetFieldHello(){
    System.out.println("====================== SetFieldHello constructed." );
  }
  
  @CommandLine.Option(names = "--fieldValue")
  private String fieldValue;
  
  private String field;
  
  public String getField() {
    return field;
  }
  
  public void setField(String field) {
    this.field = field;
  }
  
  @Override
  public Integer call() throws Exception {
    field = fieldValue ;
    return super.call();
  }
}
