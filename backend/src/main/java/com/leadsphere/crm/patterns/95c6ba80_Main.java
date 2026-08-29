package com.leadsphere.crm.patterns;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Main {
  private static final Logger logger = Logger.getLogger(Main.class.getName());

  public static void main(String[] args) {
    List<String> inputs =
        Arrays.asList(
            "Hello world hello", "MapReduce is fun", "Hello from the other side", "Hello world");
    List<Map.Entry<String, Integer>> result = MapReduce.mapReduce(inputs);
    for (Map.Entry<String, Integer> entry : result) {
      logger.info(entry.getKey() + ": " + entry.getValue());
    }
  }
}
