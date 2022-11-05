package com.essaid.context;

import lombok.Data;
import org.springframework.core.NamedInheritableThreadLocal;
import org.springframework.core.NamedThreadLocal;

@Data
class ThreadRequestInfo implements IThreadRequestInfo {
  
  static final NamedThreadLocal<IThreadRequestInfo> threadRequestInfoHolder = new NamedThreadLocal<>(
      "Thread request " + "info.");
  
  static final NamedInheritableThreadLocal<IThreadRequestInfo> inheritableThreadRequestInfoHolder = new NamedInheritableThreadLocal<>(
      "Inheritable thread " + "request info.");
  private final String sessionId;
  private final String conversationId;
  
  static IThreadRequestInfo reset() {
    IThreadRequestInfo currentInfo = get();
    
    threadRequestInfoHolder.remove();
    inheritableThreadRequestInfoHolder.remove();
    
    return currentInfo;
  }
  
  static IThreadRequestInfo set(IThreadRequestInfo threadRequestInfo, boolean inheritable) {
    if (threadRequestInfo == null) {
      return reset();
    }
    IThreadRequestInfo currentInfo = get();
    if (inheritable) {
      threadRequestInfoHolder.remove();
      inheritableThreadRequestInfoHolder.set(threadRequestInfo);
    } else {
      threadRequestInfoHolder.set(threadRequestInfo);
      inheritableThreadRequestInfoHolder.remove();
    }
    return currentInfo;
  }
  
  static IThreadRequestInfo get() {
    IThreadRequestInfo currentInfo = threadRequestInfoHolder.get();
    if (currentInfo == null) {
      currentInfo = inheritableThreadRequestInfoHolder.get();
    }
    return currentInfo;
  }
  
}
