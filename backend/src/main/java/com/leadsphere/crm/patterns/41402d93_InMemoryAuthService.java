package com.leadsphere.crm.patterns;

import com.iluwatar.bff.model.User;
import com.iluwatar.bff.service.AuthService;
import java.util.Map;

public final class InMemoryAuthService implements AuthService {

  private final Map<String, User> users;

  public InMemoryAuthService(final Map<String, User> userData) {
    this.users = userData;
  }

  @Override
  public User getUser(final String userId) {
    var user = users.get(userId);
    if (user == null) {
      throw new IllegalArgumentException("Unknown user id: " + userId);
    }
    return user;
  }
}
