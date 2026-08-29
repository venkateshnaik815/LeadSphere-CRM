package com.leadsphere.crm.patterns;

import com.iluwatar.leaderelection.AbstractMessageManager;
import com.iluwatar.leaderelection.Instance;
import com.iluwatar.leaderelection.Message;
import com.iluwatar.leaderelection.MessageType;
import java.util.Map;

public class RingMessageManager extends AbstractMessageManager {

  public RingMessageManager(Map<Integer, Instance> instanceMap) {
    super(instanceMap);
  }

  @Override
  public boolean sendHeartbeatMessage(int leaderId) {
    var leaderInstance = instanceMap.get(leaderId);
    return leaderInstance.isAlive();
  }

  @Override
  public boolean sendElectionMessage(int currentId, String content) {
    var nextInstance = this.findNextInstance(currentId);
    var electionMessage = new Message(MessageType.ELECTION, content);
    nextInstance.onMessage(electionMessage);
    return true;
  }

  @Override
  public boolean sendLeaderMessage(int currentId, int leaderId) {
    var nextInstance = this.findNextInstance(currentId);
    var leaderMessage = new Message(MessageType.LEADER, String.valueOf(leaderId));
    nextInstance.onMessage(leaderMessage);
    return true;
  }

  @Override
  public void sendHeartbeatInvokeMessage(int currentId) {
    var nextInstance = this.findNextInstance(currentId);
    var heartbeatInvokeMessage = new Message(MessageType.HEARTBEAT_INVOKE, "");
    nextInstance.onMessage(heartbeatInvokeMessage);
  }
}
