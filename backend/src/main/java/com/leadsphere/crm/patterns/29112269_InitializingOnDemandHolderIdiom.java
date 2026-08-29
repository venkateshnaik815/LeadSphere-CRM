package com.leadsphere.crm.patterns;

public final class InitializingOnDemandHolderIdiom {

  private InitializingOnDemandHolderIdiom() {
    // to prevent instantiating by Reflection call
    if (HelperHolder.INSTANCE != null) {
      throw new IllegalStateException("Already initialized.");
    }
  }

  public static InitializingOnDemandHolderIdiom getInstance() {
    return HelperHolder.INSTANCE;
  }

  private static class HelperHolder {

    private static final InitializingOnDemandHolderIdiom INSTANCE =
        new InitializingOnDemandHolderIdiom();
  }
}
