package com.leadsphere.crm.patterns;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleActor2 extends Actor {
  private final ActorSystem actorSystem;
  @Getter private final List<String> receivedMessages = new ArrayList<>();

  public ExampleActor2(ActorSystem actorSystem) {
    this.actorSystem = actorSystem;
  }

  @Override
  protected void onReceive(Message message) {
    receivedMessages.add(message.getContent());
    LOGGER.info("[{}]Received : {}", getActorId(), message.getContent());
  }
}
