package com.essaid.pcli.factory;

import picocli.CommandLine;

import java.util.List;

public interface IFactoryExtended extends CommandLine.IFactory {
  
  CommandLine.IFactory getDelegate();
  
  void reset();
  
  List<Exception> getExceptions();
  
  <K> K doCreate(Class<K> cls) throws  Exception;
  
  <K> K delegateCreate(Class<K> cls, List<Exception> exceptions) throws Exception;
  
  <K> K postProcess(K k);
  
}
