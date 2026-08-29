package com.leadsphere.crm.patterns;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

  public abstract Long getId();

  public abstract void setId(Long id);

  public abstract String getName();

  public abstract void setName(final String name);
}
