package com.leadsphere.crm.patterns;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class JpaCategoryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String type;

  public JpaCategoryEntity() {}

  public JpaCategoryEntity(Long id, String type) {
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
