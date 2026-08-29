package com.leadsphere.crm.patterns;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {

  private int key;

  private String value;

  private DataType type;

  public Data(final int key, final String value, final DataType type) {
    this.key = key;
    this.value = value;
    this.type = type;
  }

  enum DataType {
    TYPE_1,
    TYPE_2,
    TYPE_3
  }

  @Override
  public String toString() {
    return "Data {" + "key=" + key + ", value='" + value + '\'' + ", type=" + type + '}';
  }
}
