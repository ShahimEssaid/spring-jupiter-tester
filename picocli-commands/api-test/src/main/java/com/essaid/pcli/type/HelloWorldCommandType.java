package com.essaid.pcli.type;

import com.essaid.pcli.command.HelloWorldCommand;

public class HelloWorldCommandType extends  CommandType {
  public HelloWorldCommandType() {
    super("hello.world", 0, HelloWorldCommand.class, null, null);
  }
}
