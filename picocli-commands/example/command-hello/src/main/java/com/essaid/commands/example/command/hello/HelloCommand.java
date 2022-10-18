package com.essaid.commands.example.command.hello;

import com.essaid.picocli.commands.Command;
import com.essaid.picocli.commands.CommandInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import picocli.CommandLine;

import java.util.List;
import java.util.concurrent.Callable;

@CommandInfo(groupId = "com.essaid.commands.example.command", artifactId = "hello", commandId = "hello", commandVersion = "0" +
    ".0.1", description = "A demo hello and " + "echo subcommand", comments = "Some comment")
@CommandLine.Command(name = "hello", description =
    "An example hello command with an echo subcommand that echos the " + "input argument list.",
    subcommandsRepeatable = true, subcommands = HelloCommand.Echo.class)
public class HelloCommand extends Command {
  
  @Override
  public Integer call() throws Exception {
    System.out.println("============= Running hello command");
    return 0;
  }
  
  @CommandLine.Command(name = "echo")
  public static class Echo implements Callable<Integer> {
    
    @Autowired
    ApplicationContext context;
    
    List<String> arguments;
    
    @Override
    public Integer call() throws Exception {
      System.out.println("======= running echo commands with args: " + arguments + " and application context: " + context);
      return 1;
    }
  }
}
