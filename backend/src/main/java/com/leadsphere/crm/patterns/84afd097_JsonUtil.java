package com.leadsphere.crm.patterns;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtil {

  private static ObjectMapper objectMapper = new ObjectMapper();

  private JsonUtil() {}

  public static <T> String objectToJson(T object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      LOGGER.error("Cannot convert the object " + object + " to Json.", e);
      return null;
    }
  }

  public static <T> T jsonToObject(String json, Class<T> clazz) {
    try {
      return objectMapper.readValue(json, clazz);
    } catch (IOException e) {
      LOGGER.error("Cannot convert the Json " + json + " to class " + clazz.getName() + ".", e);
      return null;
    }
  }

  public static <T> List<T> jsonToList(String json, Class<T> clazz) {
    try {
      CollectionType listType =
          objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
      return objectMapper.reader().forType(listType).readValue(json);
    } catch (JsonProcessingException e) {
      LOGGER.error("Cannot convert the Json " + json + " to List of " + clazz.getName() + ".", e);
      return List.of();
    }
  }
}
