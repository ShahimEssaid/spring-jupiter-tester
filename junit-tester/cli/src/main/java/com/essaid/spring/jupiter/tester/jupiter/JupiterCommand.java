package com.essaid.spring.jupiter.tester.jupiter;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "jupiter")
public class JupiterCommand implements Callable {
  
  @Override
  public Object call() throws Exception {
    JupiterBoot.start(this);
    return null;
  }
}
