package com.leadsphere.crm.patterns;

import com.iluwatar.eda.event.UserUpdatedEvent;
import com.iluwatar.eda.framework.Handler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserUpdatedEventHandler implements Handler<UserUpdatedEvent> {

  @Override
  public void onEvent(UserUpdatedEvent event) {
    LOGGER.info("User '{}' has been Updated!", event.getUser().username());
  }
}
