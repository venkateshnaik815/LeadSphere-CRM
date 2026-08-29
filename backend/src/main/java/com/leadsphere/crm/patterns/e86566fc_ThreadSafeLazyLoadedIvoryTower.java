package com.leadsphere.crm.patterns;

public final class ThreadSafeLazyLoadedIvoryTower {

  private static volatile ThreadSafeLazyLoadedIvoryTower instance;

  private ThreadSafeLazyLoadedIvoryTower() {
    // Protect against instantiation via reflection
    if (instance != null) {
      throw new IllegalStateException("Already initialized.");
    }
  }

  public static synchronized ThreadSafeLazyLoadedIvoryTower getInstance() {
    if (instance == null) {
      instance = new ThreadSafeLazyLoadedIvoryTower();
    }
    return instance;
  }
}
