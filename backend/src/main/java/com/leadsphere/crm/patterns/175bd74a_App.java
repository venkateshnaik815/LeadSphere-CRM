package com.leadsphere.crm.patterns;

import com.iluwatar.databus.data.MessageData;
import com.iluwatar.databus.data.StartingData;
import com.iluwatar.databus.data.StoppingData;
import com.iluwatar.databus.members.MessageCollectorMember;
import com.iluwatar.databus.members.StatusMember;
import java.time.LocalDateTime;

class App {

  public static void main(String[] args) {
    final var bus = DataBus.getInstance();
    bus.subscribe(new StatusMember(1));
    bus.subscribe(new StatusMember(2));
    final var foo = new MessageCollectorMember("Foo");
    final var bar = new MessageCollectorMember("Bar");
    bus.subscribe(foo);
    bus.publish(StartingData.of(LocalDateTime.now()));
    bus.publish(MessageData.of("Only Foo should see this"));
    bus.subscribe(bar);
    bus.publish(MessageData.of("Foo and Bar should see this"));
    bus.unsubscribe(foo);
    bus.publish(MessageData.of("Only Bar should see this"));
    bus.publish(StoppingData.of(LocalDateTime.now()));
  }
}
