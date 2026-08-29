package com.leadsphere.crm.patterns;

import com.iluwatar.caching.database.DbManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheStore {
  private static final int CAPACITY = 3;

  private LruCache cache;

  private final DbManager dbManager;

  public CacheStore(final DbManager dataBaseManager) {
    this.dbManager = dataBaseManager;
    initCapacity(CAPACITY);
  }

  public void initCapacity(final int capacity) {
    if (cache == null) {
      cache = new LruCache(capacity);
    } else {
      cache.setCapacity(capacity);
    }
  }

  public UserAccount readThrough(final String userId) {
    if (cache.contains(userId)) {
      LOGGER.info("# Found in Cache!");
      return cache.get(userId);
    }
    LOGGER.info("# Not found in cache! Go to DB!!");
    UserAccount userAccount = dbManager.readFromDb(userId);
    cache.set(userId, userAccount);
    return userAccount;
  }

  public void writeThrough(final UserAccount userAccount) {
    if (cache.contains(userAccount.getUserId())) {
      dbManager.updateDb(userAccount);
    } else {
      dbManager.writeToDb(userAccount);
    }
    cache.set(userAccount.getUserId(), userAccount);
  }

  public void writeAround(final UserAccount userAccount) {
    if (cache.contains(userAccount.getUserId())) {
      dbManager.updateDb(userAccount);
      // Cache data has been updated -- remove older
      cache.invalidate(userAccount.getUserId());
      // version from cache.
    } else {
      dbManager.writeToDb(userAccount);
    }
  }

  public UserAccount readThroughWithWriteBackPolicy(final String userId) {
    if (cache.contains(userId)) {
      LOGGER.info("# Found in cache!");
      return cache.get(userId);
    }
    LOGGER.info("# Not found in Cache!");
    UserAccount userAccount = dbManager.readFromDb(userId);
    if (cache.isFull()) {
      LOGGER.info("# Cache is FULL! Writing LRU data to DB...");
      UserAccount toBeWrittenToDb = cache.getLruData();
      dbManager.upsertDb(toBeWrittenToDb);
    }
    cache.set(userId, userAccount);
    return userAccount;
  }

  public void writeBehind(final UserAccount userAccount) {
    if (cache.isFull() && !cache.contains(userAccount.getUserId())) {
      LOGGER.info("# Cache is FULL! Writing LRU data to DB...");
      UserAccount toBeWrittenToDb = cache.getLruData();
      dbManager.upsertDb(toBeWrittenToDb);
    }
    cache.set(userAccount.getUserId(), userAccount);
  }

  public void clearCache() {
    if (cache != null) {
      cache.clear();
    }
  }

  public void flushCache() {
    LOGGER.info("# flushCache...");
    Optional.ofNullable(cache)
        .map(LruCache::getCacheDataInListForm)
        .orElse(List.of())
        .forEach(dbManager::updateDb);
    dbManager.disconnect();
  }

  public String print() {
    return Optional.ofNullable(cache)
        .map(LruCache::getCacheDataInListForm)
        .orElse(List.of())
        .stream()
        .map(userAccount -> userAccount.toString() + "\n")
        .collect(Collectors.joining("", "\n--CACHE CONTENT--\n", "----"));
  }

  public UserAccount get(final String userId) {
    return cache.get(userId);
  }

  public void set(final String userId, final UserAccount userAccount) {
    cache.set(userId, userAccount);
  }

  public void invalidate(final String userId) {
    cache.invalidate(userId);
  }
}
