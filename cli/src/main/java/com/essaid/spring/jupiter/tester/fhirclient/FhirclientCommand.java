package com.essaid.spring.jupiter.tester.fhirclient;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "fhirclient")
public class FhirclientCommand implements Callable<Integer> {
  @Override
  public Integer call() throws Exception {
    return null;
  }
}
