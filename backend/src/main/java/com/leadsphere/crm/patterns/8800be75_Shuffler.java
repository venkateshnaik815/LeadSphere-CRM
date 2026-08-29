package com.leadsphere.crm.patterns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shuffler {

  private Shuffler() {
    throw new UnsupportedOperationException(
        "Shuffler is a utility class and cannot be instantiated.");
  }

  public static Map<String, List<Integer>> shuffleAndSort(List<Map<String, Integer>> mapped) {
    Map<String, List<Integer>> grouped = new HashMap<>();
    for (Map<String, Integer> map : mapped) {
      for (Map.Entry<String, Integer> entry : map.entrySet()) {
        grouped.putIfAbsent(entry.getKey(), new ArrayList<>());
        grouped.get(entry.getKey()).add(entry.getValue());
      }
    }
    return grouped;
  }
}
