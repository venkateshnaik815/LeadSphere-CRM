package com.leadsphere.crm.patterns;

import java.util.HashMap;
import java.util.Map;

public class Mapper {
  private Mapper() {
    throw new UnsupportedOperationException(
        "Mapper is a utility class and cannot be instantiated.");
  }

  public static Map<String, Integer> map(String input) {
    Map<String, Integer> wordCount = new HashMap<>();
    String[] words = input.split("\\s+");
    for (String word : words) {
      word = word.toLowerCase().replaceAll("[^a-z]", "");
      if (!word.isEmpty()) {
        wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
      }
    }
    return wordCount;
  }
}
