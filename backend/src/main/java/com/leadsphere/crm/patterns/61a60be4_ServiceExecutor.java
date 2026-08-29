package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceExecutor implements Runnable {

  private final MessageQueue msgQueue;

  public ServiceExecutor(MessageQueue msgQueue) {
    this.msgQueue = msgQueue;
  }

  public void run() {
    try {
      while (!Thread.currentThread().isInterrupted()) {
        var msg = msgQueue.retrieveMsg();

        if (null != msg) {
          LOGGER.info(msg + " is served.");
        } else {
          LOGGER.info("Service Executor: Waiting for Messages to serve .. ");
        }

        Thread.sleep(1000);
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }
  }
}
