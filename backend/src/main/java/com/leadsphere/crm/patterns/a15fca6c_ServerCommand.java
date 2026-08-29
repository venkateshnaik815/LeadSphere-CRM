package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ServerCommand {
  protected DataTransferObject data;

  public Notification getNotification() {
    return data.getNotification();
  }
}
