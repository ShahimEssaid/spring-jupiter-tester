package com.essaid.pcli.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommandTypeRegistrant implements ICommandTypeRegistrant {
  private List<ICommandType> allCommandTypes = new ArrayList<>();
  
  
  @Override
  public List<ICommandType> register(ICommandTypeRegistry registry) {
    List<ICommandType> types = new ArrayList<>();
    allCommandTypes.forEach(toRegisterType -> {
      String id = toRegisterType.getCommandTypeId();
      List<ICommandType> existingTypes = registry.getCommandTypes().get(id);
      if (existingTypes != null) {
        for (ICommandType existingType : existingTypes) {
          if (existingType.getRegistrationKey().equals(toRegisterType.getRegistrationKey())) return;
        }
      }
      registry.addCommandType(toRegisterType);
      types.add(toRegisterType);
    });
    
    return types;
  }
}
