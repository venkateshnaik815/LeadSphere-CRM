package com.leadsphere.crm.patterns;

import java.lang.reflect.Field;
import java.util.StringJoiner;

public class FieldJsonMapper {

  public String toJson(Video video, String[] fields) throws Exception {
    var json = new StringJoiner(",", "{", "}");

    var i = 0;
    var fieldsLength = fields.length;
    while (i < fieldsLength) {
      json.add(getString(video, Video.class.getDeclaredField(fields[i])));
      i++;
    }

    return json.toString();
  }

  private String getString(Video video, Field declaredField) throws IllegalAccessException {
    declaredField.setAccessible(true);
    var value = declaredField.get(video);
    if (declaredField.get(video) instanceof Integer) {
      return "\"" + declaredField.getName() + "\"" + ": " + value;
    }
    return "\"" + declaredField.getName() + "\"" + ": " + "\"" + value.toString() + "\"";
  }
}
