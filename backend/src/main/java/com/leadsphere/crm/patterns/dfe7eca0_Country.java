package com.leadsphere.crm.patterns;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Country implements Serializable {

  private int code;
  private String name;
  private String continents;
  private String language;
  @Serial private static final long serialVersionUID = 7149851;
}
