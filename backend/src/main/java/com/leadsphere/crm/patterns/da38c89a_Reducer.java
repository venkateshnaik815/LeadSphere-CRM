package com.leadsphere.crm.patterns;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reducer {
  private Reducer() {
    throw new UnsupportedOperationException(
        "Reducer is a utility class and cannot be instantiated.");
  }

  public static List<Map.Entry<String, Integer>> reduce(Map<String, List<Integer>> grouped) {
    Map<String, Integer> reduced = new HashMap<>();
    for (Map.Entry<String, List<Integer>> entry : grouped.entrySet()) {
      reduced.put(entry.getKey(), entry.getValue().stream().mapToInt(Integer::intValue).sum());
    }

    List<Map.Entry<String, Integer>> result = new ArrayList<>(reduced.entrySet());
    result.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
    return result;
  }
}
