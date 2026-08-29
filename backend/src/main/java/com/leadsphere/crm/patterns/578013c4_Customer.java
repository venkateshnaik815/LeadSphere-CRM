package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class Customer {

  @EqualsAndHashCode.Include private int id;
  private String firstName;
  private String lastName;
}
