package com.leadsphere.crm.patterns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Message {
  private final String msg;

  @Override
  public String toString() {
    return msg;
  }
}
