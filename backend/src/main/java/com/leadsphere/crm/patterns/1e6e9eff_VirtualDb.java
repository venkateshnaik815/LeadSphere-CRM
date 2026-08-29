package com.leadsphere.crm.patterns;

import com.iluwatar.caching.UserAccount;
import java.util.HashMap;
import java.util.Map;

public class VirtualDb implements DbManager {
  private Map<String, UserAccount> db;

  @Override
  public void connect() {
    db = new HashMap<>();
  }

  @Override
  public void disconnect() {
    db = null;
  }

  @Override
  public UserAccount readFromDb(final String userId) {
    if (db.containsKey(userId)) {
      return db.get(userId);
    }
    return null;
  }

  @Override
  public UserAccount writeToDb(final UserAccount userAccount) {
    db.put(userAccount.getUserId(), userAccount);
    return userAccount;
  }

  @Override
  public UserAccount updateDb(final UserAccount userAccount) {
    return writeToDb(userAccount);
  }

  @Override
  public UserAccount upsertDb(final UserAccount userAccount) {
    return updateDb(userAccount);
  }
}
