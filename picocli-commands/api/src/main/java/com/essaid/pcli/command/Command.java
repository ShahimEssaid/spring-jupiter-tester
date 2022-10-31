package com.essaid.pcli.command;

import com.essaid.pcli.type.ICommandType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public abstract class Command implements ICommand {
  
  @Getter
  @Setter
  private ICommandType commandType;
  
  @Override
  public void run() {
    
    try {
      call();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
