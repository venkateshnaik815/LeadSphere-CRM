package com.leadsphere.crm.patterns;

public final class DbManagerFactory {
  private DbManagerFactory() {}

  public static DbManager initDb(final boolean isMongo) {
    if (isMongo) {
      return new MongoDb();
    }
    return new VirtualDb();
  }
}
