package com.leadsphere.crm.patterns;

import com.iluwatar.leaderelection.AbstractInstance;
import com.iluwatar.leaderelection.Message;
import com.iluwatar.leaderelection.MessageManager;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RingInstance extends AbstractInstance {
  private static final String INSTANCE = "Instance ";

  public RingInstance(MessageManager messageManager, int localId, int leaderId) {
    super(messageManager, localId, leaderId);
  }

  @Override
  protected void handleHeartbeatInvokeMessage() {
    try {
      var isLeaderAlive = messageManager.sendHeartbeatMessage(this.leaderId);
      if (isLeaderAlive) {
        LOGGER.info(INSTANCE + localId + "- Leader is alive. Start next heartbeat in 5 second.");
        Thread.sleep(HEARTBEAT_INTERVAL);
        messageManager.sendHeartbeatInvokeMessage(this.localId);
      } else {
        LOGGER.info(INSTANCE + localId + "- Leader is not alive. Start election.");
        messageManager.sendElectionMessage(this.localId, String.valueOf(this.localId));
      }
    } catch (InterruptedException e) {
      LOGGER.info(INSTANCE + localId + "- Interrupted.");
    }
  }

  @Override
  protected void handleElectionMessage(Message message) {
    var content = message.getContent();
    LOGGER.info(INSTANCE + localId + " - Election Message: " + content);
    var candidateList =
        Arrays.stream(content.trim().split(",")).map(Integer::valueOf).sorted().toList();
    if (candidateList.contains(localId)) {
      var newLeaderId = candidateList.get(0);
      LOGGER.info(INSTANCE + localId + " - New leader should be " + newLeaderId + ".");
      messageManager.sendLeaderMessage(localId, newLeaderId);
    } else {
      content += "," + localId;
      messageManager.sendElectionMessage(localId, content);
    }
  }

  @Override
  protected void handleLeaderMessage(Message message) {
    var newLeaderId = Integer.valueOf(message.getContent());
    if (this.leaderId != newLeaderId) {
      LOGGER.info(INSTANCE + localId + " - Update leaderID");
      this.leaderId = newLeaderId;
      messageManager.sendLeaderMessage(localId, newLeaderId);
    } else {
      LOGGER.info(INSTANCE + localId + " - Leader update done. Start heartbeat.");
      messageManager.sendHeartbeatInvokeMessage(localId);
    }
  }

  @Override
  protected void handleLeaderInvokeMessage() {
    // Not used in Ring instance.
  }

  @Override
  protected void handleHeartbeatMessage(Message message) {
    // Not used in Ring instance.
  }

  @Override
  protected void handleElectionInvokeMessage() {
    // Not used in Ring instance.
  }
}
