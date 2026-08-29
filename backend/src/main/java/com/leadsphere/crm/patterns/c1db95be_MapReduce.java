package com.leadsphere.crm.patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapReduce {
  private MapReduce() {
    throw new UnsupportedOperationException(
        "MapReduce is a utility class and cannot be instantiated.");
  }

  public static List<Map.Entry<String, Integer>> mapReduce(List<String> inputs) {
    List<Map<String, Integer>> mapped = new ArrayList<>();
    for (String input : inputs) {
      mapped.add(Mapper.map(input));
    }

    Map<String, List<Integer>> grouped = Shuffler.shuffleAndSort(mapped);

    return Reducer.reduce(grouped);
  }
}
