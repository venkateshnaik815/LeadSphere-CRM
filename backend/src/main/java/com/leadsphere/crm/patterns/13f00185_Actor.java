package com.leadsphere.crm.patterns;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import lombok.Getter;
import lombok.Setter;

public abstract class Actor implements Runnable {

  @Setter @Getter private String actorId;
  private final BlockingQueue<Message> mailbox = new LinkedBlockingQueue<>();
  private volatile boolean active =
      true; // always read from main memory and written back to main memory,

  // rather than being cached in a thread's local memory. To make it consistent to all Actors

  public void send(Message message) {
    mailbox.add(message); // Add message to queue
  }

  public void stop() {
    active = false; // Stop the actor loop
  }

  @Override
  public void run() {
    while (active) {
      try {
        Message message = mailbox.take(); // Wait for a message
        onReceive(message); // Process it
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  // Child classes must define what to do with a message
  protected abstract void onReceive(Message message);
}
