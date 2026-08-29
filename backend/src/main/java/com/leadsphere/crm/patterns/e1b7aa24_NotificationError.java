package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotificationError {
  private int errorId;
  private String errorMessage;

  @Override
  public String toString() {
    return "Error " + errorId + ": " + errorMessage;
  }
}
