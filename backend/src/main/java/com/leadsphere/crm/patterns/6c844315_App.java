package com.leadsphere.crm.patterns;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    var alice = new ImmutableUser("Alice", 30, List.of("admin", "user"));
    LOGGER.info("Original user: {}", alice);

    var olderAlice = alice.withAge(31);
    LOGGER.info("Updated user (new object): {}", olderAlice);
    LOGGER.info("Original is unchanged: {}", alice);

    // Demonstrate defensive copy: mutating the source list does not affect alice
    var mutableRoles = new java.util.ArrayList<>(List.of("viewer"));
    var bob = new ImmutableUser("Bob", 25, mutableRoles);
    mutableRoles.add("editor");
    LOGGER.info("Bob's roles (unchanged despite external list mutation): {}", bob.getRoles());
  }
}
