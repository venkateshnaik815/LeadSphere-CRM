package com.leadsphere.crm.patterns;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String username;
  private String name;
  private String email;

  public Author(String username, String name, String email) {
    this.username = username;
    this.name = name;
    this.email = email;
  }

  protected Author() {}
}
