package com.essaid.picocli.commands.factory;

import picocli.CommandLine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class PicoCollectionFactory extends  AbstractFactory {
  
  public PicoCollectionFactory(CommandLine.IFactory delegateFactory) {
    super(delegateFactory);
  }
  
  @Override
  public  <K> K doCreate(Class<K> cls) throws Exception {
    if (cls.isInterface()) {
      if (Collection.class.isAssignableFrom(cls)) {
        if (List.class.isAssignableFrom(cls)) {
          return cls.cast(new ArrayList<Object>());
        } else if (SortedSet.class.isAssignableFrom(cls)) {
          return cls.cast(new TreeSet<Object>());
        } else if (Set.class.isAssignableFrom(cls)) {
          return cls.cast(new LinkedHashSet<Object>());
        } else if (Queue.class.isAssignableFrom(cls)) {
          return cls.cast(new LinkedList<Object>()); // ArrayDeque is only available since 1.6
        } else {
          return cls.cast(new ArrayList<Object>());
        }
      }
    }
    return null;
  }
}
