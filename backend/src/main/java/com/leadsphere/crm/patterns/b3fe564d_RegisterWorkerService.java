package com.leadsphere.crm.patterns;

public class RegisterWorkerService {
  public void registerWorker(RegisterWorkerDto registration) {
    var cmd = new RegisterWorker(registration);
    cmd.run();
  }
}
