package com.leadsphere.crm.patterns;

import com.iluwatar.eda.event.UserCreatedEvent;
import com.iluwatar.eda.event.UserUpdatedEvent;
import com.iluwatar.eda.framework.Event;
import com.iluwatar.eda.framework.EventDispatcher;
import com.iluwatar.eda.handler.UserCreatedEventHandler;
import com.iluwatar.eda.handler.UserUpdatedEventHandler;
import com.iluwatar.eda.model.User;

public class App {

  public static void main(String[] args) {

    var dispatcher = new EventDispatcher();
    dispatcher.registerHandler(UserCreatedEvent.class, new UserCreatedEventHandler());
    dispatcher.registerHandler(UserUpdatedEvent.class, new UserUpdatedEventHandler());

    var user = new User("iluwatar");
    dispatcher.dispatch(new UserCreatedEvent(user));
    dispatcher.dispatch(new UserUpdatedEvent(user));
  }
}
