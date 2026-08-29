package com.leadsphere.crm.patterns;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractInstance implements Instance, Runnable {

  protected static final int HEARTBEAT_INTERVAL = 5000;
  private static final String INSTANCE = "Instance ";

  protected MessageManager messageManager;
  protected Queue<Message> messageQueue;
  protected final int localId;
  protected int leaderId;
  protected boolean alive;

  public AbstractInstance(MessageManager messageManager, int localId, int leaderId) {
    this.messageManager = messageManager;
    this.messageQueue = new ConcurrentLinkedQueue<>();
    this.localId = localId;
    this.leaderId = leaderId;
    this.alive = true;
  }

  @Override
  @SuppressWarnings("squid:S2189")
  public void run() {
    while (true) {
      if (!this.messageQueue.isEmpty()) {
        this.processMessage(this.messageQueue.remove());
      }
    }
  }

  @Override
  public void onMessage(Message message) {
    messageQueue.offer(message);
  }

  @Override
  public boolean isAlive() {
    return alive;
  }

  @Override
  public void setAlive(boolean alive) {
    this.alive = alive;
  }

  private void processMessage(Message message) {
    switch (message.getType()) {
      case ELECTION -> {
        LOGGER.info(INSTANCE + localId + " - Election Message handling...");
        handleElectionMessage(message);
      }
      case LEADER -> {
        LOGGER.info(INSTANCE + localId + " - Leader Message handling...");
        handleLeaderMessage(message);
      }
      case HEARTBEAT -> {
        LOGGER.info(INSTANCE + localId + " - Heartbeat Message handling...");
        handleHeartbeatMessage(message);
      }
      case ELECTION_INVOKE -> {
        LOGGER.info(INSTANCE + localId + " - Election Invoke Message handling...");
        handleElectionInvokeMessage();
      }
      case LEADER_INVOKE -> {
        LOGGER.info(INSTANCE + localId + " - Leader Invoke Message handling...");
        handleLeaderInvokeMessage();
      }
      case HEARTBEAT_INVOKE -> {
        LOGGER.info(INSTANCE + localId + " - Heartbeat Invoke Message handling...");
        handleHeartbeatInvokeMessage();
      }
      default -> {}
    }
  }

  protected abstract void handleElectionMessage(Message message);

  protected abstract void handleElectionInvokeMessage();

  protected abstract void handleLeaderMessage(Message message);

  protected abstract void handleLeaderInvokeMessage();

  protected abstract void handleHeartbeatMessage(Message message);

  protected abstract void handleHeartbeatInvokeMessage();
}
