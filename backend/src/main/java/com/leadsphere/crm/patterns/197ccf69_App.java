package com.leadsphere.crm.patterns;

import com.iluwatar.caching.database.DbManager;
import com.iluwatar.caching.database.DbManagerFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
  private static final String USE_MONGO_DB = "--mongo";

  private final AppManager appManager;

  public App(final boolean isMongo) {
    DbManager dbManager = DbManagerFactory.initDb(isMongo);
    appManager = new AppManager(dbManager);
    appManager.initDb();
  }

  public static void main(final String[] args) {
    // VirtualDB (instead of MongoDB) was used in running the JUnit tests
    // and the App class to avoid Maven compilation errors. Set flag to
    // true to run the tests with MongoDB (provided that MongoDB is
    // installed and socket connection is open).
    boolean isDbMongo = isDbMongo(args);
    if (isDbMongo) {
      LOGGER.info("Using the Mongo database engine to run the application.");
    } else {
      LOGGER.info("Using the 'in Memory' database to run the application.");
    }
    App app = new App(isDbMongo);
    app.useReadAndWriteThroughStrategy();
    String splitLine = "==============================================";
    LOGGER.info(splitLine);
    app.useReadThroughAndWriteAroundStrategy();
    LOGGER.info(splitLine);
    app.useReadThroughAndWriteBehindStrategy();
    LOGGER.info(splitLine);
    app.useCacheAsideStrategy();
    LOGGER.info(splitLine);
  }

  private static boolean isDbMongo(final String[] args) {
    for (String arg : args) {
      if (arg.equals(USE_MONGO_DB)) {
        return true;
      }
    }
    return false;
  }

  public void useReadAndWriteThroughStrategy() {
    LOGGER.info("# CachingPolicy.THROUGH");
    appManager.initCachingPolicy(CachingPolicy.THROUGH);

    var userAccount1 = new UserAccount("001", "John", "He is a boy.");

    appManager.save(userAccount1);
    LOGGER.info(appManager.printCacheContent());
    appManager.find("001");
    appManager.find("001");
  }

  public void useReadThroughAndWriteAroundStrategy() {
    LOGGER.info("# CachingPolicy.AROUND");
    appManager.initCachingPolicy(CachingPolicy.AROUND);

    var userAccount2 = new UserAccount("002", "Jane", "She is a girl.");

    appManager.save(userAccount2);
    LOGGER.info(appManager.printCacheContent());
    appManager.find("002");
    LOGGER.info(appManager.printCacheContent());
    userAccount2 = appManager.find("002");
    userAccount2.setUserName("Jane G.");
    appManager.save(userAccount2);
    LOGGER.info(appManager.printCacheContent());
    appManager.find("002");
    LOGGER.info(appManager.printCacheContent());
    appManager.find("002");
  }

  public void useReadThroughAndWriteBehindStrategy() {
    LOGGER.info("# CachingPolicy.BEHIND");
    appManager.initCachingPolicy(CachingPolicy.BEHIND);

    var userAccount3 = new UserAccount("003", "Adam", "He likes food.");
    var userAccount4 = new UserAccount("004", "Rita", "She hates cats.");
    var userAccount5 = new UserAccount("005", "Isaac", "He is allergic to mustard.");

    appManager.save(userAccount3);
    appManager.save(userAccount4);
    appManager.save(userAccount5);
    LOGGER.info(appManager.printCacheContent());
    appManager.find("003");
    LOGGER.info(appManager.printCacheContent());
    UserAccount userAccount6 = new UserAccount("006", "Yasha", "She is an only child.");
    appManager.save(userAccount6);
    LOGGER.info(appManager.printCacheContent());
    appManager.find("004");
    LOGGER.info(appManager.printCacheContent());
  }

  public void useCacheAsideStrategy() {
    LOGGER.info("# CachingPolicy.ASIDE");
    appManager.initCachingPolicy(CachingPolicy.ASIDE);
    LOGGER.info(appManager.printCacheContent());

    var userAccount3 = new UserAccount("003", "Adam", "He likes food.");
    var userAccount4 = new UserAccount("004", "Rita", "She hates cats.");
    var userAccount5 = new UserAccount("005", "Isaac", "He is allergic to mustard.");
    appManager.save(userAccount3);
    appManager.save(userAccount4);
    appManager.save(userAccount5);

    LOGGER.info(appManager.printCacheContent());
    appManager.find("003");
    LOGGER.info(appManager.printCacheContent());
    appManager.find("004");
    LOGGER.info(appManager.printCacheContent());
  }
}
