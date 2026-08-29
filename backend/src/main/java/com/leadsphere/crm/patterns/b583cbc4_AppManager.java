package com.leadsphere.crm.patterns;

import com.iluwatar.caching.database.DbManager;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppManager {
  private CachingPolicy cachingPolicy;

  private final DbManager dbManager;

  private final CacheStore cacheStore;

  public AppManager(final DbManager newDbManager) {
    this.dbManager = newDbManager;
    this.cacheStore = new CacheStore(newDbManager);
  }

  public void initDb() {
    dbManager.connect();
  }

  public void initCachingPolicy(final CachingPolicy policy) {
    cachingPolicy = policy;
    if (cachingPolicy == CachingPolicy.BEHIND) {
      Runtime.getRuntime().addShutdownHook(new Thread(cacheStore::flushCache));
    }
    cacheStore.clearCache();
  }

  public UserAccount find(final String userId) {
    LOGGER.info("Trying to find {} in cache", userId);
    if (cachingPolicy == CachingPolicy.THROUGH || cachingPolicy == CachingPolicy.AROUND) {
      return cacheStore.readThrough(userId);
    } else if (cachingPolicy == CachingPolicy.BEHIND) {
      return cacheStore.readThroughWithWriteBackPolicy(userId);
    } else if (cachingPolicy == CachingPolicy.ASIDE) {
      return findAside(userId);
    }
    return null;
  }

  public void save(final UserAccount userAccount) {
    LOGGER.info("Save record!");
    if (cachingPolicy == CachingPolicy.THROUGH) {
      cacheStore.writeThrough(userAccount);
    } else if (cachingPolicy == CachingPolicy.AROUND) {
      cacheStore.writeAround(userAccount);
    } else if (cachingPolicy == CachingPolicy.BEHIND) {
      cacheStore.writeBehind(userAccount);
    } else if (cachingPolicy == CachingPolicy.ASIDE) {
      saveAside(userAccount);
    }
  }

  public String printCacheContent() {
    return cacheStore.print();
  }

  private void saveAside(final UserAccount userAccount) {
    dbManager.updateDb(userAccount);
    cacheStore.invalidate(userAccount.getUserId());
  }

  private UserAccount findAside(final String userId) {
    return Optional.ofNullable(cacheStore.get(userId))
        .or(
            () -> {
              Optional<UserAccount> userAccount = Optional.ofNullable(dbManager.readFromDb(userId));
              userAccount.ifPresent(account -> cacheStore.set(userId, account));
              return userAccount;
            })
        .orElse(null);
  }
}
