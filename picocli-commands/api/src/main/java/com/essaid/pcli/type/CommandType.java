package com.essaid.pcli.type;

import com.essaid.pcli.util.IRegistrable;
import com.essaid.util.asserts.Asserts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CommandType implements ICommandType, IRegistrable {
  
  private String commandTypeId;
  private int order;
  private Class<?> commandClass;
  private Object commandFactory;
  private Object executionStrategy;
  private Object registrationKey;
  
  //private String rootCommandTypeId;
  public CommandType(String commandTypeId,
                     //String rootCommandTypeId,
                     int order, Class<?> commandClass, Object commandFactory, Object executionStrategy) {
    Asserts.allNotNull("Can't construct CommandType with null values: ", commandTypeId, commandClass);
    setCommandTypeId(commandTypeId);
    //setRootCommandTypeId(rootCommandTypeId);
    this.order = order;
    this.commandClass = commandClass;
    this.commandFactory = commandFactory;
    this.executionStrategy = executionStrategy;
  }
  
  static private void validateId(String id) {
    if (!id.contains(".") || id.startsWith(".")) {
      throw new IllegalArgumentException("id in ICommandType must contain at least one dot, " + "and not " + "start " + "with a dot but " + "was provided:" + id + " for type id or root type id.");
    }
  }
  
  public void setCommandTypeId(String commandTypeId) {
    validateId(commandTypeId);
    this.commandTypeId = commandTypeId;
  }

//  public void setRootCommandTypeId(String rootCommandTypeId) {
//    validateId(rootCommandTypeId);
//    this.rootCommandTypeId = rootCommandTypeId;
//  }

//  @Override
//  public String toString() {
//    return "ICommandType[id: " + commandTypeId + ", commandClass: " + getCommandClass() + ", factory:" +
//    commandFactory + ", " + "execution: " + executionStrategy + "]";
//  }
  
  @Override
  public int compareTo(ICommandType o) {
    return getOrder() - o.getOrder();
  }
}
