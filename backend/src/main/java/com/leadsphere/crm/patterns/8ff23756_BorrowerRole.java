package com.leadsphere.crm.patterns;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowerRole extends CustomerRole {

  private String name;

  public String borrow() {
    return String.format("Borrower %s wants to get some money.", name);
  }
}
