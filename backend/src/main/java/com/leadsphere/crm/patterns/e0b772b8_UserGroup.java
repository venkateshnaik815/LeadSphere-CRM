package com.leadsphere.crm.patterns;

import java.util.ArrayList;
import java.util.List;

public class UserGroup {

  private static final List<User> freeGroup = new ArrayList<>();
  private static final List<User> paidGroup = new ArrayList<>();

  public static void addUserToFreeGroup(final User user) throws IllegalArgumentException {
    if (paidGroup.contains(user)) {
      throw new IllegalArgumentException("User already member of paid group.");
    } else {
      if (!freeGroup.contains(user)) {
        freeGroup.add(user);
      }
    }
  }

  public static void addUserToPaidGroup(final User user) throws IllegalArgumentException {
    if (freeGroup.contains(user)) {
      throw new IllegalArgumentException("User already member of free group.");
    } else {
      if (!paidGroup.contains(user)) {
        paidGroup.add(user);
      }
    }
  }

  public static boolean isPaid(User user) {
    return paidGroup.contains(user);
  }
}
