package com.leadsphere.crm.patterns;

import com.iluwatar.leaderelection.AbstractMessageManager;
import com.iluwatar.leaderelection.Instance;
import com.iluwatar.leaderelection.Message;
import com.iluwatar.leaderelection.MessageType;
import java.util.List;
import java.util.Map;

public class BullyMessageManager extends AbstractMessageManager {

  public BullyMessageManager(Map<Integer, Instance> instanceMap) {
    super(instanceMap);
  }

  @Override
  public boolean sendHeartbeatMessage(int leaderId) {
    var leaderInstance = instanceMap.get(leaderId);
    return leaderInstance.isAlive();
  }

  @Override
  public boolean sendElectionMessage(int currentId, String content) {
    var candidateList = findElectionCandidateInstanceList(currentId);
    if (candidateList.isEmpty()) {
      return true;
    } else {
      var electionMessage = new Message(MessageType.ELECTION_INVOKE, "");
      candidateList.forEach((i) -> instanceMap.get(i).onMessage(electionMessage));
      return false;
    }
  }

  @Override
  public boolean sendLeaderMessage(int currentId, int leaderId) {
    var leaderMessage = new Message(MessageType.LEADER, String.valueOf(leaderId));
    instanceMap.keySet().stream()
        .filter((i) -> i != currentId)
        .forEach((i) -> instanceMap.get(i).onMessage(leaderMessage));
    return false;
  }

  @Override
  public void sendHeartbeatInvokeMessage(int currentId) {
    var nextInstance = this.findNextInstance(currentId);
    var heartbeatInvokeMessage = new Message(MessageType.HEARTBEAT_INVOKE, "");
    nextInstance.onMessage(heartbeatInvokeMessage);
  }

  private List<Integer> findElectionCandidateInstanceList(int currentId) {
    return instanceMap.keySet().stream()
        .filter((i) -> i < currentId && instanceMap.get(i).isAlive())
        .toList();
  }
}
