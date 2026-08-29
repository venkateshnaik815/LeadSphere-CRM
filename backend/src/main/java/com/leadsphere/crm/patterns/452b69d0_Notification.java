package com.leadsphere.crm.patterns;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Notification {

  private final List<NotificationError> errors = new ArrayList<>();

  public boolean hasErrors() {
    return !this.errors.isEmpty();
  }

  public void addError(NotificationError error) {
    this.errors.add(error);
  }
}
