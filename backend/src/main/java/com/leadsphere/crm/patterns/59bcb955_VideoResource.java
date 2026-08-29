package com.leadsphere.crm.patterns;

import java.util.Map;

public record VideoResource(FieldJsonMapper fieldJsonMapper, Map<Integer, Video> videos) {
  public String getDetails(Integer id, String... fields) throws Exception {
    if (fields.length == 0) {
      return videos.get(id).toString();
    }
    return fieldJsonMapper.toJson(videos.get(id), fields);
  }
}
