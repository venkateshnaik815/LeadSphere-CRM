package com.leadsphere.crm.patterns;

import lombok.Getter;

public class ChapterResult<K> {
  @Getter private final K value;
  private final State state;

  ChapterResult(K value, State state) {
    this.value = value;
    this.state = state;
  }

  public boolean isSuccess() {
    return state == State.SUCCESS;
  }

  public static <K> ChapterResult<K> success(K val) {
    return new ChapterResult<>(val, State.SUCCESS);
  }

  public static <K> ChapterResult<K> failure(K val) {
    return new ChapterResult<>(val, State.FAILURE);
  }

  public enum State {
    SUCCESS,
    FAILURE
  }
}
