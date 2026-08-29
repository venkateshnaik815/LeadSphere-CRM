package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Message {
  private final String content;
  private final String senderId;
}
