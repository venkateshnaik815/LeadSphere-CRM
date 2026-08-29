package com.leadsphere.crm.patterns;

import com.iluwatar.eda.model.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserCreatedEvent extends AbstractEvent {

  private final User user;
}
