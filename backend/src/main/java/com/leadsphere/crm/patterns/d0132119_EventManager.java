package com.leadsphere.crm.patterns;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;

public class EventManager implements ThreadCompleteListener {

  public static final int MAX_RUNNING_EVENTS = 1000;
  // Just don't want to have too many running events. :)
  public static final int MIN_ID = 1;
  public static final int MAX_ID = MAX_RUNNING_EVENTS;
  public static final Duration MAX_EVENT_TIME = Duration.ofSeconds(1800); // 30 minutes.
  private int currentlyRunningSyncEvent = -1;
  private final SecureRandom rand;

  @Getter private final Map<Integer, AsyncEvent> eventPool;

  private static final String DOES_NOT_EXIST = " does not exist.";

  public EventManager() {
    rand = new SecureRandom();
    eventPool = new ConcurrentHashMap<>(MAX_RUNNING_EVENTS);
  }

  public int create(Duration eventTime)
      throws MaxNumOfEventsAllowedException, InvalidOperationException, LongRunningEventException {
    if (currentlyRunningSyncEvent != -1) {
      throw new InvalidOperationException(
          "Event ["
              + currentlyRunningSyncEvent
              + "] is still"
              + " running. Please wait until it finishes and try again.");
    }

    var eventId = createEvent(eventTime, true);
    currentlyRunningSyncEvent = eventId;

    return eventId;
  }

  public int createAsync(Duration eventTime)
      throws MaxNumOfEventsAllowedException, LongRunningEventException {
    return createEvent(eventTime, false);
  }

  private int createEvent(Duration eventTime, boolean isSynchronous)
      throws MaxNumOfEventsAllowedException, LongRunningEventException {
    if (eventTime.isNegative()) {
      throw new IllegalArgumentException("eventTime cannot be negative");
    }

    if (eventPool.size() == MAX_RUNNING_EVENTS) {
      throw new MaxNumOfEventsAllowedException(
          "Too many events are running at the moment." + " Please try again later.");
    }

    if (eventTime.getSeconds() > MAX_EVENT_TIME.getSeconds()) {
      throw new LongRunningEventException(
          "Maximum event time allowed is " + MAX_EVENT_TIME + " seconds. Please try again.");
    }

    var newEventId = generateId();

    var newEvent = new AsyncEvent(newEventId, eventTime, isSynchronous);
    newEvent.addListener(this);
    eventPool.put(newEventId, newEvent);

    return newEventId;
  }

  public void start(int eventId) throws EventDoesNotExistException {
    if (!eventPool.containsKey(eventId)) {
      throw new EventDoesNotExistException(eventId + DOES_NOT_EXIST);
    }

    eventPool.get(eventId).start();
  }

  public void cancel(int eventId) throws EventDoesNotExistException {
    if (!eventPool.containsKey(eventId)) {
      throw new EventDoesNotExistException(eventId + DOES_NOT_EXIST);
    }

    if (eventId == currentlyRunningSyncEvent) {
      currentlyRunningSyncEvent = -1;
    }

    eventPool.get(eventId).stop();
    eventPool.remove(eventId);
  }

  public void status(int eventId) throws EventDoesNotExistException {
    if (!eventPool.containsKey(eventId)) {
      throw new EventDoesNotExistException(eventId + DOES_NOT_EXIST);
    }

    eventPool.get(eventId).status();
  }

  @SuppressWarnings("rawtypes")
  public void statusOfAllEvents() {
    eventPool.entrySet().forEach(entry -> ((AsyncEvent) ((Map.Entry) entry).getValue()).status());
  }

  @SuppressWarnings("rawtypes")
  public void shutdown() {
    eventPool.entrySet().forEach(entry -> ((AsyncEvent) ((Map.Entry) entry).getValue()).stop());
  }

  private int generateId() {
    // nextInt is normally exclusive of the top value,
    // so add 1 to make it inclusive
    var randomNum = rand.nextInt((MAX_ID - MIN_ID) + 1) + MIN_ID;
    while (eventPool.containsKey(randomNum)) {
      randomNum = rand.nextInt((MAX_ID - MIN_ID) + 1) + MIN_ID;
    }

    return randomNum;
  }

  @Override
  public void completedEventHandler(int eventId) {
    eventPool.get(eventId).status();
    if (eventPool.get(eventId).isSynchronous()) {
      currentlyRunningSyncEvent = -1;
    }
    eventPool.remove(eventId);
  }

  public int numOfCurrentlyRunningSyncEvent() {
    return currentlyRunningSyncEvent;
  }
}
