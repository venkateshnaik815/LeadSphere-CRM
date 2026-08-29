package com.leadsphere.crm.patterns;

import java.time.LocalDate;
import java.time.Period;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RegisterWorker extends ServerCommand {
  static final int LEGAL_AGE = 18;

  protected RegisterWorker(RegisterWorkerDto worker) {
    super(worker);
  }

  public void run() {

    validate();
    if (!super.getNotification().hasErrors()) {
      LOGGER.info("Register worker in backend system");
    }
  }

  private void validate() {
    var ourData = ((RegisterWorkerDto) this.data);
    // check if any of submitted data is not given
    // passing for empty value validation
    fail(isNullOrBlank(ourData.getName()), RegisterWorkerDto.MISSING_NAME);
    fail(isNullOrBlank(ourData.getOccupation()), RegisterWorkerDto.MISSING_OCCUPATION);
    fail(isNullOrBlank(ourData.getDateOfBirth()), RegisterWorkerDto.MISSING_DOB);

    if (isNullOrBlank(ourData.getDateOfBirth())) {
      // If DOB is null or empty
      fail(true, RegisterWorkerDto.MISSING_DOB);
    } else {
      // Validating age ( should be greater than or equal to 18 )
      Period age = Period.between(ourData.getDateOfBirth(), LocalDate.now());
      fail(age.getYears() < LEGAL_AGE, RegisterWorkerDto.DOB_TOO_SOON);
    }
  }

  protected boolean isNullOrBlank(Object obj) {
    if (obj == null) {
      return true;
    }

    if (obj instanceof String) {
      return ((String) obj).trim().isEmpty();
    }

    return false;
  }

  protected void fail(boolean condition, NotificationError error) {
    if (condition) {
      super.getNotification().addError(error);
    }
  }
}
