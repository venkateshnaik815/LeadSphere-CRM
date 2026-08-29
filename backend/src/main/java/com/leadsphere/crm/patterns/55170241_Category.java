package com.leadsphere.crm.patterns;

import com.iluwatar.onion.domain.exception.DomainException;

public class Category {

  private final Long id;
  private final String type;

  public Category(Long id, String type) {
    if (type == null || type.isEmpty()) {
      throw new DomainException("Type is null or empty. Category type is required.");
    }
    this.id = id;
    this.type = type;
  }

  public Long getId() {
    return id;
  }

  public String getType() {
    return type;
  }
}
