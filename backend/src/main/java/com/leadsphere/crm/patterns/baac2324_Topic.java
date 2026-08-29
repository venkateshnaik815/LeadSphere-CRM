package com.leadsphere.crm.patterns;

import com.iluwatar.publish.subscribe.subscriber.Subscriber;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArraySet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Topic {

  private final String topicName;
  private final Set<Subscriber> subscribers = new CopyOnWriteArraySet<>();

  public void addSubscriber(Subscriber subscriber) {
    subscribers.add(subscriber);
  }

  public void removeSubscriber(Subscriber subscriber) {
    subscribers.remove(subscriber);
  }

  public void publish(Message message) {
    for (Subscriber subscriber : subscribers) {
      CompletableFuture.runAsync(() -> subscriber.onMessage(message));
    }
  }
}
