package com.leadsphere.crm.patterns;

import com.iluwatar.poison.pill.Message.Headers;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Producer {

  private final MqPublishPoint queue;
  private final String name;
  private boolean isStopped;

  public Producer(String name, MqPublishPoint queue) {
    this.name = name;
    this.queue = queue;
    this.isStopped = false;
  }

  public void send(String body) {
    if (isStopped) {
      throw new IllegalStateException(
          String.format(
              "Producer %s was stopped and fail to deliver requested message [%s].", body, name));
    }
    var msg = new SimpleMessage();
    msg.addHeader(Headers.DATE, new Date().toString());
    msg.addHeader(Headers.SENDER, name);
    msg.setBody(body);

    try {
      queue.put(msg);
    } catch (InterruptedException e) {
      // allow thread to exit
      LOGGER.error("Exception caught.", e);
    }
  }

  public void stop() {
    isStopped = true;
    try {
      queue.put(Message.POISON_PILL);
    } catch (InterruptedException e) {
      // allow thread to exit
      LOGGER.error("Exception caught.", e);
    }
  }
}
