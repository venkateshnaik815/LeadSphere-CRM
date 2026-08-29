package com.leadsphere.crm.patterns;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class Order { // can store all transactions ids also

  enum PaymentStatus {
    NOT_DONE,
    TRYING,
    DONE
  }

  enum MessageSent {
    NONE_SENT,
    PAYMENT_FAIL,
    PAYMENT_TRYING,
    PAYMENT_SUCCESSFUL
  }

  final User user;
  final String item;
  public final String id;
  final float price;
  final long createdTime;
  private static final SecureRandom RANDOM = new SecureRandom();
  private static final String ALL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
  private static final Map<String, Boolean> USED_IDS = new HashMap<>();
  PaymentStatus paid;
  MessageSent messageSent; // to avoid sending error msg on page and text more than once
  boolean addedToEmployeeHandle; // to avoid creating more to enqueue

  Order(User user, String item, float price) {
    this.createdTime = System.currentTimeMillis();
    this.user = user;
    this.item = item;
    this.price = price;
    String id = createUniqueId();
    if (USED_IDS.get(id) != null) {
      while (USED_IDS.get(id)) {
        id = createUniqueId();
      }
    }
    this.id = id;
    USED_IDS.put(this.id, true);
    this.paid = PaymentStatus.TRYING;
    this.messageSent = MessageSent.NONE_SENT;
    this.addedToEmployeeHandle = false;
  }

  private String createUniqueId() {
    StringBuilder random = new StringBuilder();
    while (random.length() < 12) { // length of the random string.
      int index = (int) (RANDOM.nextFloat() * ALL_CHARS.length());
      random.append(ALL_CHARS.charAt(index));
    }
    return random.toString();
  }
}
