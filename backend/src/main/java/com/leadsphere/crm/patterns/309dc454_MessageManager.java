package com.leadsphere.crm.patterns;

public interface MessageManager {

  boolean sendHeartbeatMessage(int leaderId);

  boolean sendElectionMessage(int currentId, String content);

  boolean sendLeaderMessage(int currentId, int leaderId);

  void sendHeartbeatInvokeMessage(int currentId);
}
