package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskHandler {

  public void handleTask(Task task) throws InterruptedException {
    var time = task.getTime();
    Thread.sleep(time);
    LOGGER.info("It takes " + time + " milliseconds to finish the task");
    task.setFinished(true);
  }
}
