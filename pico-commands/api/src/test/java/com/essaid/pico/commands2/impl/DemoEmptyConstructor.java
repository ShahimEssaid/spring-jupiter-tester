package com.essaid.pico.commands2.impl;

import com.essaid.pico.commands2.AbstractPCommandType2;
import com.essaid.pico.commands2.PCommandType2;
import picocli.CommandLine;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class DemoEmptyConstructor extends AbstractPCommandType2 {
  static final String NAME = "demo-empty-constructor";
  
  public DemoEmptyConstructor() {
//    super(NAME, NAME, new String[]{NAME}, DemoEmptyConstructorCli1.class);
    super(NAME, NAME, new String[]{NAME}, DemoEmptyConstructorCli2.class);
//    super("demo-empty-constructor", "demo-empty-constructor", new String[]{"demo-empty-constructor"},
//        DemoEmptyConstructorCli3.class);
//    super("demo-empty-constructor", "demo-empty-constructor", new String[]{"demo-empty-constructor"},
//        DemoEmptyConstructorCli4.class);
//    super("demo-empty-constructor", "demo-empty-constructor", new String[]{"demo-empty-constructor"},
//        DemoEmptyConstructorCli5.class);
  }
  
  @Override
  public CommandLine createCommandLine() {
    try {
      return createCommandLine(this);
    } catch (InvocationTargetException e) {
      throw new RuntimeException(e);
    } catch (InstantiationException e) {
      throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
  
  @CommandLine.Command(name = NAME)
  public static class DemoEmptyConstructorCli1 {
    public DemoEmptyConstructorCli1() {
    }
  }
  
  @CommandLine.Command(name = NAME)
  public static class DemoEmptyConstructorCli2 {
    private final PCommandType2 type;
    
    public DemoEmptyConstructorCli2() {
      type = null;
    }
    
    public DemoEmptyConstructorCli2(PCommandType2 type) {
      this.type = type;
    }
  }
  
  @CommandLine.Command(name = NAME)
  public static class DemoEmptyConstructorCli3 {
    private final PCommandType2 type;
    
    public DemoEmptyConstructorCli3() {
      type = null;
    }
    
    public DemoEmptyConstructorCli3(PCommandType2 type, PCommandType2 type2) {
      this.type = type;
    }
  }
  
  @CommandLine.Command(name = NAME)
  public static class DemoEmptyConstructorCli4 {
    private final PCommandType2 type;
    private final Integer integer;
    
    public DemoEmptyConstructorCli4(PCommandType2 type) {
      this.type = type;
      integer = null;
    }
    
    public DemoEmptyConstructorCli4(PCommandType2 type, Integer integer) {
      this.type = type;
      this.integer = integer;
    }
  }
  @CommandLine.Command(name = NAME)
  public class DemoEmptyConstructorCli5 {
    private final PCommandType2 type;
    private final Integer integer;
    
    
    public DemoEmptyConstructorCli5(PCommandType2 type, Integer integer) {
      this.type = type;
      this.integer = integer;
    }
  }
  
}
