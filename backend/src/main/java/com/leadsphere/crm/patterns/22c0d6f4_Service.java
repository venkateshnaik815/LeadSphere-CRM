package com.leadsphere.crm.patterns;

import com.iluwatar.commander.exceptions.DatabaseUnavailableException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public abstract class Service {

  protected final Database database;
  public ArrayList<Exception> exceptionsList;
  private static final SecureRandom RANDOM = new SecureRandom();
  private static final String ALL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
  private static final Hashtable<String, Boolean> USED_IDS = new Hashtable<>();

  protected Service(Database db, Exception... exc) {
    this.database = db;
    this.exceptionsList = new ArrayList<>(List.of(exc));
  }

  public abstract String receiveRequest(Object... parameters) throws DatabaseUnavailableException;

  protected abstract String updateDb(Object... parameters) throws DatabaseUnavailableException;

  protected String generateId() {
    StringBuilder random = new StringBuilder();
    while (random.length() < 12) { // length of the random string.
      int index = (int) (RANDOM.nextFloat() * ALL_CHARS.length());
      random.append(ALL_CHARS.charAt(index));
    }
    String id = random.toString();
    if (USED_IDS.get(id) != null) {
      while (USED_IDS.get(id)) {
        id = generateId();
      }
    }
    return id;
  }
}
