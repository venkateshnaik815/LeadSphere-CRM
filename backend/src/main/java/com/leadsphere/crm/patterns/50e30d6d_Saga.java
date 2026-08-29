package com.leadsphere.crm.patterns;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class Saga {

  private final List<Chapter> chapters;

  private Saga() {
    this.chapters = new ArrayList<>();
  }

  public Saga chapter(String name) {
    this.chapters.add(new Chapter(name));
    return this;
  }

  public Chapter get(int idx) {
    return chapters.get(idx);
  }

  public boolean isPresent(int idx) {
    return idx >= 0 && idx < chapters.size();
  }

  public static Saga create() {
    return new Saga();
  }

  public enum Result {
    FINISHED,
    ROLLBACK,
    CRASHED
  }

  @AllArgsConstructor
  @Getter
  public static class Chapter {
    String name;
  }
}
