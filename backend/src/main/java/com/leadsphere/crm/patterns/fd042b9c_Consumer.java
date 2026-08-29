package com.leadsphere.crm.patterns;

import com.iluwatar.poison.pill.Message.Headers;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Consumer {

  private final MqSubscribePoint queue;
  private final String name;

  public Consumer(String name, MqSubscribePoint queue) {
    this.name = name;
    this.queue = queue;
  }

  public void consume() {
    while (true) {
      try {
        var msg = queue.take();
        if (Message.POISON_PILL.equals(msg)) {
          LOGGER.info("Consumer {} receive request to terminate.", name);
          break;
        }
        var sender = msg.getHeader(Headers.SENDER);
        var body = msg.getBody();
        LOGGER.info("Message [{}] from [{}] received by [{}]", body, sender, name);
      } catch (InterruptedException e) {
        // allow thread to exit
        LOGGER.error("Exception caught.", e);
        return;
      }
    }
  }
}
