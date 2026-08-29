package com.leadsphere.crm.patterns;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter
@Setter
@AllArgsConstructor
public final class Student implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  @EqualsAndHashCode.Include private int studentId;
  private String name;
  private char grade;
}
