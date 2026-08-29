package com.leadsphere.crm.patterns;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageQueue {

  private final BlockingQueue<Message> blkQueue;

  // Default constructor when called creates Blocking Queue object.
  public MessageQueue() {
    this.blkQueue = new ArrayBlockingQueue<>(1024);
  }

  public void submitMsg(Message msg) {
    try {
      if (null != msg) {
        blkQueue.add(msg);
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }
  }

  public Message retrieveMsg() {
    try {
      return blkQueue.poll();
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }
    return null;
  }
}
