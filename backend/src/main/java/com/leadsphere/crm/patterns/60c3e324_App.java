
package com.leadsphere.crm.patterns;

public class App {
  public static void main(String[] args) throws InterruptedException {
    ActorSystem system = new ActorSystem();
    Actor srijan = new ExampleActor(system);
    Actor ansh = new ExampleActor2(system);

    system.startActor(srijan);
    system.startActor(ansh);
    ansh.send(new Message("Hello ansh", srijan.getActorId()));
    srijan.send(new Message("Hello srijan!", ansh.getActorId()));

    Thread.sleep(1000); // Give time for messages to process

    srijan.stop(); // Stop the actor gracefully
    ansh.stop();
    system.shutdown(); // Stop the actor system
  }
}
