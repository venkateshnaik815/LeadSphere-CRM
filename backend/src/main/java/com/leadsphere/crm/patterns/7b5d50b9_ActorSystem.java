package com.leadsphere.crm.patterns;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ActorSystem {
  private final ExecutorService executor = Executors.newCachedThreadPool();
  private final ConcurrentHashMap<String, Actor> actorRegister = new ConcurrentHashMap<>();
  private final AtomicInteger idCounter = new AtomicInteger(0);

  public void startActor(Actor actor) {
    String actorId = "actor-" + idCounter.incrementAndGet(); // Generate a new and unique ID
    actor.setActorId(actorId); // assign the actor it's ID
    actorRegister.put(actorId, actor); // Register and save the actor with it's ID
    executor.submit(actor); // Run the actor in a thread
  }

  public Actor getActorById(String actorId) {
    return actorRegister.get(actorId); //  Find by Id
  }

  public void shutdown() {
    executor.shutdownNow(); // Stop all threads
  }
}
