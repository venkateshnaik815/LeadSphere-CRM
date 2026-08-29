package com.leadsphere.crm.patterns;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BallThread extends Thread {

  @Setter private BallItem twin;

  private volatile boolean isSuspended;

  private volatile boolean isRunning = true;

  public void run() {

    while (isRunning) {
      if (!isSuspended) {
        twin.draw();
        twin.move();
      }
      try {
        Thread.sleep(250);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void suspendMe() {
    isSuspended = true;
    LOGGER.info("Begin to suspend BallThread");
  }

  public void resumeMe() {
    isSuspended = false;
    LOGGER.info("Begin to resume BallThread");
  }

  public void stopMe() {
    this.isRunning = false;
    this.isSuspended = true;
  }
}
