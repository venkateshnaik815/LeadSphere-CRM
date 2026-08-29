package com.leadsphere.crm.patterns;

import com.iluwatar.masterworker.Input;
import com.iluwatar.masterworker.Result;
import com.iluwatar.masterworker.system.systemworkers.Worker;
import java.util.Hashtable;
import java.util.List;
import lombok.Getter;

public abstract class Master {
  private final int numOfWorkers;
  private final List<Worker> workers;
  private final Hashtable<Integer, Result<?>> allResultData;
  private int expectedNumResults;
  @Getter private Result<?> finalResult;

  Master(int numOfWorkers) {
    this.numOfWorkers = numOfWorkers;
    this.workers = setWorkers(numOfWorkers);
    this.expectedNumResults = 0;
    this.allResultData = new Hashtable<>(numOfWorkers);
    this.finalResult = null;
  }

  Hashtable<Integer, Result<?>> getAllResultData() {
    return this.allResultData;
  }

  int getExpectedNumResults() {
    return this.expectedNumResults;
  }

  List<Worker> getWorkers() {
    return this.workers;
  }

  abstract List<Worker> setWorkers(int num);

  public void doWork(Input<?> input) {
    divideWork(input);
  }

  private void divideWork(Input<?> input) {
    var dividedInput = input.divideData(numOfWorkers);
    if (dividedInput != null) {
      this.expectedNumResults = dividedInput.size();
      for (var i = 0; i < this.expectedNumResults; i++) {
        // ith division given to ith worker in this.workers
        this.workers.get(i).setReceivedData(this, dividedInput.get(i));
        this.workers.get(i).start();
      }
      for (var i = 0; i < this.expectedNumResults; i++) {
        try {
          this.workers.get(i).join();
        } catch (InterruptedException e) {
          System.err.println("Error while executing thread");
        }
      }
    }
  }

  public void receiveData(Result<?> data, Worker w) {
    // check if we can receive... if yes:
    collectResult(data, w.getWorkerId());
  }

  private void collectResult(Result<?> data, int workerId) {
    this.allResultData.put(workerId, data);
    if (this.allResultData.size() == this.expectedNumResults) {
      // all data received
      this.finalResult = aggregateData();
    }
  }

  abstract Result<?> aggregateData();
}
