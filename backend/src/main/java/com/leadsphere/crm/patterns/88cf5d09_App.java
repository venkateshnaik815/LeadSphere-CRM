package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {

    // Simple lazy loader - not thread safe
    var holderNaive = new HolderNaive();
    var heavy = holderNaive.getHeavy();
    LOGGER.info("heavy={}", heavy);

    // Thread safe lazy loader, but with heavy synchronization on each access
    var holderThreadSafe = new HolderThreadSafe();
    var another = holderThreadSafe.getHeavy();
    LOGGER.info("another={}", another);

    // The most efficient lazy loader utilizing Java 8 features
    var java8Holder = new Java8Holder();
    var next = java8Holder.getHeavy();
    LOGGER.info("next={}", next);
  }
}
