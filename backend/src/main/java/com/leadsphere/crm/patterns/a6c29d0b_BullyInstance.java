package com.leadsphere.crm.patterns;

import com.iluwatar.leaderelection.AbstractInstance;
import com.iluwatar.leaderelection.Message;
import com.iluwatar.leaderelection.MessageManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BullyInstance extends AbstractInstance {
  private static final String INSTANCE = "Instance ";

  public BullyInstance(MessageManager messageManager, int localId, int leaderId) {
    super(messageManager, localId, leaderId);
  }

  @Override
  protected void handleHeartbeatInvokeMessage() {
    try {
      boolean isLeaderAlive = messageManager.sendHeartbeatMessage(leaderId);
      if (isLeaderAlive) {
        LOGGER.info(INSTANCE + localId + "- Leader is alive.");
        Thread.sleep(HEARTBEAT_INTERVAL);
        messageManager.sendHeartbeatInvokeMessage(localId);
      } else {
        LOGGER.info(INSTANCE + localId + "- Leader is not alive. Start election.");
        boolean electionResult =
            messageManager.sendElectionMessage(localId, String.valueOf(localId));
        if (electionResult) {
          LOGGER.info(INSTANCE + localId + "- Succeed in election. Start leader notification.");
          messageManager.sendLeaderMessage(localId, localId);
        }
      }
    } catch (InterruptedException e) {
      LOGGER.info(INSTANCE + localId + "- Interrupted.");
    }
  }

  @Override
  protected void handleElectionInvokeMessage() {
    if (!isLeader()) {
      LOGGER.info(INSTANCE + localId + "- Start election.");
      boolean electionResult = messageManager.sendElectionMessage(localId, String.valueOf(localId));
      if (electionResult) {
        LOGGER.info(INSTANCE + localId + "- Succeed in election. Start leader notification.");
        leaderId = localId;
        messageManager.sendLeaderMessage(localId, localId);
        messageManager.sendHeartbeatInvokeMessage(localId);
      }
    }
  }

  @Override
  protected void handleLeaderMessage(Message message) {
    leaderId = Integer.parseInt(message.getContent());
    LOGGER.info(INSTANCE + localId + " - Leader update done.");
  }

  private boolean isLeader() {
    return localId == leaderId;
  }

  @Override
  protected void handleLeaderInvokeMessage() {
    // Not used in Bully Instance
  }

  @Override
  protected void handleHeartbeatMessage(Message message) {
    // Not used in Bully Instance
  }

  @Override
  protected void handleElectionMessage(Message message) {
    // Not used in Bully Instance
  }
}
