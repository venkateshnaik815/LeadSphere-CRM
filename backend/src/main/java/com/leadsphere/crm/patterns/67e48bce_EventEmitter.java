package com.leadsphere.crm.patterns;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class EventEmitter {

  private final Map<Event, List<EventObserver>> observerLists;

  public EventEmitter() {
    observerLists = new HashMap<>();
  }

  public EventEmitter(EventObserver obs, Event e) {
    this();
    registerObserver(obs, e);
  }

  public final void registerObserver(EventObserver obs, Event e) {
    if (!observerLists.containsKey(e)) {
      observerLists.put(e, new LinkedList<>());
    }
    if (!observerLists.get(e).contains(obs)) {
      observerLists.get(e).add(obs);
    }
  }

  protected void notifyObservers(Event e) {
    if (observerLists.containsKey(e)) {
      observerLists.get(e).forEach(observer -> observer.onEvent(e));
    }
  }

  public abstract void timePasses(Weekday day);
}
