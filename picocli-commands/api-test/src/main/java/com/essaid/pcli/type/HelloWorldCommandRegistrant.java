package com.essaid.pcli.type;

import com.essaid.pcli.command.HelloWorldCommand;

import java.util.Collections;
import java.util.List;

public class HelloWorldCommandRegistrant extends CommandTypeRegistrant{
  public HelloWorldCommandRegistrant() {
    HelloWorldCommandType type = new HelloWorldCommandType();
    type.setRegistrationKey(HelloWorldCommandRegistrant.class);
    setAllCommandTypes(Collections.singletonList(type));
  }
}
