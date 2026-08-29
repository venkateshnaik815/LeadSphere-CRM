package com.leadsphere.crm.patterns;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Character {

  private String name;
  private String fighterClass;
  private String wizardClass;
  private String weapon;
  private String spell;
  private List<String> abilities;

  public Character(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return new StringBuilder()
        .append("This is a ")
        .append(fighterClass != null ? fighterClass : wizardClass)
        .append(" named ")
        .append(name)
        .append(" armed with a ")
        .append(weapon != null ? weapon : spell != null ? spell : "with nothing")
        .append(abilities != null ? " and wielding " + abilities + " abilities" : "")
        .append('.')
        .toString();
  }
}
