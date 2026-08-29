package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskGenerator implements Task, Runnable {

  // MessageQueue reference using which we will submit our messages.
  private final MessageQueue msgQueue;

  // Total message count that a TaskGenerator will submit.
  private final int msgCount;

  // Parameterized constructor.
  public TaskGenerator(MessageQueue msgQueue, int msgCount) {
    this.msgQueue = msgQueue;
    this.msgCount = msgCount;
  }

  public void submit(Message msg) {
    try {
      this.msgQueue.submitMsg(msg);
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }
  }

  public void run() {
    var count = this.msgCount;

    try {
      while (count > 0) {
        var statusMsg = "Message-" + count + " submitted by " + Thread.currentThread().getName();
        this.submit(new Message(statusMsg));

        LOGGER.info(statusMsg);

        // reduce the message count.
        count--;

        // Make the current thread to sleep after every Message submission.
        Thread.sleep(1000);
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }
  }
}
