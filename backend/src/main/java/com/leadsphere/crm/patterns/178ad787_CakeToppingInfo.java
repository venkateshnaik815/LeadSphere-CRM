
package com.leadsphere.crm.patterns;

public class CakeToppingInfo {

  public final Long id;
  public final String name;
  public final int calories;

  public CakeToppingInfo(Long id, String name, int calories) {
    this.id = id;
    this.name = name;
    this.calories = calories;
  }

  public CakeToppingInfo(String name, int calories) {
    this.id = null;
    this.name = name;
    this.calories = calories;
  }

  @Override
  public String toString() {
    return String.format("CakeToppingInfo id=%d name=%s calories=%d", id, name, calories);
  }
}
