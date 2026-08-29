package com.leadsphere.crm.patterns;

import com.iluwatar.eda.event.UserCreatedEvent;
import com.iluwatar.eda.framework.Handler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserCreatedEventHandler implements Handler<UserCreatedEvent> {

  @Override
  public void onEvent(UserCreatedEvent event) {
    LOGGER.info("User '{}' has been Created!", event.getUser().username());
  }
}
