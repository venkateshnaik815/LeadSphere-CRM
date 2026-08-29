package com.leadsphere.crm.patterns;

public final class IvoryTower {

  private IvoryTower() {
    // to prevent instantiating by Reflection call
    if (INSTANCE != null) {
      throw new IllegalStateException("Already initialized.");
    }
  }

  private static final IvoryTower INSTANCE = new IvoryTower();

  public static IvoryTower getInstance() {
    return INSTANCE;
  }
}
