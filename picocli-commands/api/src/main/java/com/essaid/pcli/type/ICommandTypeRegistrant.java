package com.essaid.pcli.type;

import java.util.List;

public interface ICommandTypeRegistrant {
 List<ICommandType> register(ICommandTypeRegistry registry);
}
