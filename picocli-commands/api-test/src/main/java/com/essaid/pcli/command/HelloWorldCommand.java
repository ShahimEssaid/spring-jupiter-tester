package com.essaid.pcli.command;

import picocli.CommandLine;

@CommandLine.Command(name = "hello-world")
public class HelloWorldCommand extends Command {
  
  HelloWorldCommand(){
    System.out.println("====================== HelloWorldCommand constructed." );
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
    System.out.println("========== HelloWorldCommand  field: " + field);
    return 0;
  }
}
