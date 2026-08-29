package com.leadsphere.crm.patterns;

import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RegisterWorkerForm {
  String name;
  String occupation;
  LocalDate dateOfBirth;
  RegisterWorkerDto worker;
  RegisterWorkerService service = new RegisterWorkerService();

  public RegisterWorkerForm(String name, String occupation, LocalDate dateOfBirth) {
    this.name = name;
    this.occupation = occupation;
    this.dateOfBirth = dateOfBirth;
  }

  public void submit() {
    // Transmit information to our transfer object to communicate between layers
    saveToWorker();
    // call the service layer to register our worker
    service.registerWorker(worker);

    // check for any errors
    if (worker.getNotification().hasErrors()) {
      indicateErrors();
      LOGGER.info("Not registered, see errors");
    } else {
      LOGGER.info("Registration Succeeded");
    }
  }

  private void saveToWorker() {
    worker = new RegisterWorkerDto();
    worker.setName(name);
    worker.setOccupation(occupation);
    worker.setDateOfBirth(dateOfBirth);
  }

  public void indicateErrors() {
    worker.getNotification().getErrors().forEach(error -> LOGGER.error(error.toString()));
  }
}
