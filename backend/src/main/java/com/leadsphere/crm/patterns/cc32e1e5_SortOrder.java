package com.leadsphere.crm.patterns;

import lombok.Getter;

public enum SortOrder {
  ASC("asc"),
  DESC("desc");

  @Getter private String value;

  SortOrder(String value) {
    this.value = value;
  }
}
