package com.essaid.context;

public interface IThreadRequestInfo {
  
  static IThreadRequestInfo get() {
    return ThreadRequestInfo.get();
  }
  
  static IThreadRequestInfo set(IThreadRequestInfo threadRequestInfo, boolean inheritable) {
    return ThreadRequestInfo.set(threadRequestInfo, inheritable);
  }
  
  static IThreadRequestInfo reset() {
    return ThreadRequestInfo.reset();
  }
  
  
  String getSessionId();
  
  String getConversationId();
  
}
