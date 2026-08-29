package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {

    var messenger = new Messenger();

    LOGGER.info("Message from the orcs: ");
    messenger.messageFromOrcs().print();

    LOGGER.info("Message from the elves: ");
    messenger.messageFromElves().print();
  }
}
