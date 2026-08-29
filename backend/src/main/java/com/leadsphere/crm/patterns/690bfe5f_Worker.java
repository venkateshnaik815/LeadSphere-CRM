package com.leadsphere.crm.patterns;

import com.iluwatar.masterworker.Input;
import com.iluwatar.masterworker.Result;
import com.iluwatar.masterworker.system.systemmaster.Master;
import lombok.Getter;

public abstract class Worker extends Thread {
  private final Master master;
  @Getter private final int workerId;
  private Input<?> receivedData;

  Worker(Master master, int id) {
    this.master = master;
    this.workerId = id;
    this.receivedData = null;
  }

  Input<?> getReceivedData() {
    return this.receivedData;
  }

  public void setReceivedData(Master m, Input<?> i) {
    // check if we are ready to receive... if yes:
    this.receivedData = i;
  }

  abstract Result<?> executeOperation();

  private void sendToMaster(Result<?> data) {
    this.master.receiveData(data, this);
  }

  public void run() { // from Thread class
    var work = executeOperation();
    sendToMaster(work);
  }
}
