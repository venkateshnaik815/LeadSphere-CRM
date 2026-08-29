
package com.leadsphere.crm.patterns;

public class CakeLayerInfo {

  public final Long id;
  public final String name;
  public final int calories;

  public CakeLayerInfo(Long id, String name, int calories) {
    this.id = id;
    this.name = name;
    this.calories = calories;
  }

  public CakeLayerInfo(String name, int calories) {
    this.id = null;
    this.name = name;
    this.calories = calories;
  }

  @Override
  public String toString() {
    return String.format("CakeLayerInfo id=%d name=%s calories=%d", id, name, calories);
  }
}
