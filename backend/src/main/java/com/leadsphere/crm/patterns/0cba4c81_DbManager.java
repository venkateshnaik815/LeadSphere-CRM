package com.leadsphere.crm.patterns;

import com.iluwatar.caching.UserAccount;

public interface DbManager {
  void connect();

  void disconnect();

  UserAccount readFromDb(String userId);

  UserAccount writeToDb(UserAccount userAccount);

  UserAccount updateDb(UserAccount userAccount);

  UserAccount upsertDb(UserAccount userAccount);
}
