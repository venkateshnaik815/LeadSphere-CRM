package com.leadsphere.crm.patterns;

public class UserContextProxy {
  private static final ThreadLocal<UserContext> userContextHolder = new ThreadLocal<>();

  private UserContextProxy() {}

  public static void set(UserContext context) {
    userContextHolder.set(context);
  }

  public static UserContext get() {
    return userContextHolder.get();
  }

  public static void clear() {
    userContextHolder.remove();
  }
}
