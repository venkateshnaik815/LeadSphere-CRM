package com.leadsphere.crm.patterns;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AsyncEvent implements Event, Runnable {

  private final int eventId;
  private final Duration eventTime;
  @Getter private final boolean synchronous;
  private Thread thread;
  private final AtomicBoolean isComplete = new AtomicBoolean(false);
  private ThreadCompleteListener eventListener;

  @Override
  public void start() {
    thread = new Thread(this);
    thread.start();
  }

  @Override
  public void stop() {
    if (null == thread) {
      return;
    }
    thread.interrupt();
  }

  @Override
  public void status() {
    if (isComplete.get()) {
      LOGGER.info("[{}] is not done.", eventId);
    } else {
      LOGGER.info("[{}] is done.", eventId);
    }
  }

  @Override
  public void run() {

    var currentTime = Instant.now();
    var endTime = currentTime.plusSeconds(eventTime.getSeconds());
    while (Instant.now().compareTo(endTime) < 0) {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        LOGGER.error("Thread was interrupted: ", e);
        Thread.currentThread().interrupt();
        return;
      }
    }
    isComplete.set(true);
    completed();
  }

  public final void addListener(final ThreadCompleteListener listener) {
    this.eventListener = listener;
  }

  private void completed() {
    if (eventListener != null) {
      eventListener.completedEventHandler(eventId);
    }
  }
}
