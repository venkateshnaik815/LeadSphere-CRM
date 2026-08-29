package com.leadsphere.crm.patterns;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleActor extends Actor {
  private final ActorSystem actorSystem;
  @Getter private final List<String> receivedMessages = new ArrayList<>();

  public ExampleActor(ActorSystem actorSystem) {
    this.actorSystem = actorSystem;
  }

  // Logger log = Logger.getLogger(getClass().getName());

  @Override
  protected void onReceive(Message message) {
    LOGGER.info(
        "[{}]Received : {} from : [{}]", getActorId(), message.getContent(), message.getSenderId());
    Actor sender = actorSystem.getActorById(message.getSenderId()); // sender actor id
    // Reply of the message
    if (sender != null && !message.getSenderId().equals(getActorId())) {
      sender.send(new Message("I got your message ", getActorId()));
    }
  }
}
