package com.leadsphere.crm.patterns;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterWorkerDto extends DataTransferObject {
  private String name;
  private String occupation;
  private LocalDate dateOfBirth;

  public static final NotificationError MISSING_NAME = new NotificationError(1, "Name is missing");

  public static final NotificationError MISSING_OCCUPATION =
      new NotificationError(2, "Occupation is missing");

  public static final NotificationError MISSING_DOB =
      new NotificationError(3, "Date of birth is missing");

  public static final NotificationError DOB_TOO_SOON =
      new NotificationError(4, "Worker registered must be over 18");

  protected RegisterWorkerDto() {
    super();
  }

  public void setupWorkerDto(String name, String occupation, LocalDate dateOfBirth) {
    this.name = name;
    this.occupation = occupation;
    this.dateOfBirth = dateOfBirth;
  }
}
