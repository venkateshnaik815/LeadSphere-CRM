package com.leadsphere.crm.patterns;

public class Scout extends EventEmitter {

  public Scout() {}

  public Scout(EventObserver obs, Event e) {
    super(obs, e);
  }

  @Override
  public void timePasses(Weekday day) {
    if (day == Weekday.TUESDAY) {
      notifyObservers(Event.WARSHIPS_APPROACHING);
    }
    if (day == Weekday.WEDNESDAY) {
      notifyObservers(Event.WHITE_WALKERS_SIGHTED);
    }
  }
}
