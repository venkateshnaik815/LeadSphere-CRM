package com.leadsphere.crm.patterns;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class User {
  private Integer id;
  private String username;
  private String password;

  public User() {}

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }
}
