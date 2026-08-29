package com.leadsphere.crm.patterns;

public final class BillPughImplementation {

  private BillPughImplementation() {
    // to prevent instantiating by Reflection call
    if (InstanceHolder.instance != null) {
      throw new IllegalStateException("Already initialized.");
    }
  }

  private static class InstanceHolder {
    private static BillPughImplementation instance = new BillPughImplementation();
  }

  // global access point
  public static BillPughImplementation getInstance() {
    return InstanceHolder.instance;
  }
}
