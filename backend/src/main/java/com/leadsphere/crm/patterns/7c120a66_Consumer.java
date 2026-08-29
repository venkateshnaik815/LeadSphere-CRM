package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Consumer {

  private final ItemQueue queue;

  private final String name;

  public Consumer(String name, ItemQueue queue) {
    this.name = name;
    this.queue = queue;
  }

  public void consume() throws InterruptedException {
    var item = queue.take();
    LOGGER.info(
        "Consumer [{}] consume item [{}] produced by [{}]", name, item.id(), item.producer());
  }
}
