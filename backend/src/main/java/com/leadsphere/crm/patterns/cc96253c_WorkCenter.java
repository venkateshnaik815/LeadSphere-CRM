package com.leadsphere.crm.patterns;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.Getter;

public class WorkCenter {

  @Getter private Worker leader;
  private final List<Worker> workers = new CopyOnWriteArrayList<>();

  public void createWorkers(int numberOfWorkers, TaskSet taskSet, TaskHandler taskHandler) {
    for (var id = 1; id <= numberOfWorkers; id++) {
      var worker = new Worker(id, this, taskSet, taskHandler);
      workers.add(worker);
    }
    promoteLeader();
  }

  public void addWorker(Worker worker) {
    workers.add(worker);
  }

  public void removeWorker(Worker worker) {
    workers.remove(worker);
  }

  public void promoteLeader() {
    Worker leader = null;
    if (!workers.isEmpty()) {
      leader = workers.get(0);
    }
    this.leader = leader;
  }

  public List<Worker> getWorkers() {
    return workers;
  }
}
