package com.leadsphere.crm.patterns;

import com.iluwatar.leaderelection.Instance;
import com.iluwatar.leaderelection.Message;
import com.iluwatar.leaderelection.MessageType;
import java.util.HashMap;
import java.util.Map;

public class RingApp {

  public static void main(String[] args) {

    Map<Integer, Instance> instanceMap = new HashMap<>();
    var messageManager = new RingMessageManager(instanceMap);

    var instance1 = new RingInstance(messageManager, 1, 1);
    var instance2 = new RingInstance(messageManager, 2, 1);
    var instance3 = new RingInstance(messageManager, 3, 1);
    var instance4 = new RingInstance(messageManager, 4, 1);
    var instance5 = new RingInstance(messageManager, 5, 1);

    instanceMap.put(1, instance1);
    instanceMap.put(2, instance2);
    instanceMap.put(3, instance3);
    instanceMap.put(4, instance4);
    instanceMap.put(5, instance5);

    instance2.onMessage(new Message(MessageType.HEARTBEAT_INVOKE, ""));

    final var thread1 = new Thread(instance1);
    final var thread2 = new Thread(instance2);
    final var thread3 = new Thread(instance3);
    final var thread4 = new Thread(instance4);
    final var thread5 = new Thread(instance5);

    thread1.start();
    thread2.start();
    thread3.start();
    thread4.start();
    thread5.start();

    instance1.setAlive(false);
  }
}
