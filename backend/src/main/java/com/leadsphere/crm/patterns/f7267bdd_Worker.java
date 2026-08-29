package com.leadsphere.crm.patterns;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Slf4j
public class Worker implements Runnable {

  @EqualsAndHashCode.Include private final long id;
  private final WorkCenter workCenter;
  private final TaskSet taskSet;
  private final TaskHandler taskHandler;

  public Worker(long id, WorkCenter workCenter, TaskSet taskSet, TaskHandler taskHandler) {
    super();
    this.id = id;
    this.workCenter = workCenter;
    this.taskSet = taskSet;
    this.taskHandler = taskHandler;
  }

  @Override
  public void run() {
    while (!Thread.interrupted()) {
      try {
        if (workCenter.getLeader() != null && !workCenter.getLeader().equals(this)) {
          synchronized (workCenter) {
            if (workCenter.getLeader() != null && !workCenter.getLeader().equals(this)) {
              workCenter.wait();
              continue;
            }
          }
        }
        final Task task = taskSet.getTask();
        synchronized (workCenter) {
          workCenter.removeWorker(this);
          workCenter.promoteLeader();
          workCenter.notifyAll();
        }
        taskHandler.handleTask(task);
        LOGGER.info("The Worker with the ID " + id + " completed the task");
        workCenter.addWorker(this);
      } catch (InterruptedException e) {
        LOGGER.warn("Worker interrupted");
        Thread.currentThread().interrupt();
        return;
      }
    }
  }
}
