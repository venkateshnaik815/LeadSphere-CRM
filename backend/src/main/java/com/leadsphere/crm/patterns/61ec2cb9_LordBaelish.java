package com.leadsphere.crm.patterns;

public class LordBaelish extends EventEmitter {

  public LordBaelish() {}

  public LordBaelish(EventObserver obs, Event e) {
    super(obs, e);
  }

  @Override
  public void timePasses(Weekday day) {
    if (day == Weekday.FRIDAY) {
      notifyObservers(Event.STARK_SIGHTED);
    }
  }
}
