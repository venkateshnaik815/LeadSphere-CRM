package com.leadsphere.crm.patterns;

import lombok.Getter;

public enum RemoteServiceStatus {
  FAILURE(-1);

  @Getter private final long remoteServiceStatusValue;

  RemoteServiceStatus(long remoteServiceStatusValue) {
    this.remoteServiceStatusValue = remoteServiceStatusValue;
  }
}
