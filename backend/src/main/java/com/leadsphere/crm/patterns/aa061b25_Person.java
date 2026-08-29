package com.leadsphere.crm.patterns;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@AllArgsConstructor
public final class Person implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  @EqualsAndHashCode.Include private int personNationalId;
  private String name;
  private long phoneNum;

  @Override
  public String toString() {

    return "Person ID is : "
        + personNationalId
        + " ; Person Name is : "
        + name
        + " ; Phone Number is :"
        + phoneNum;
  }
}
