package com.leadsphere.crm.patterns;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Message {
  private final String id;
  private final String content;
  private final LocalDateTime timestamp;

  public Message(String content) {
    this.id = UUID.randomUUID().toString();
    this.content = content;
    this.timestamp = LocalDateTime.now();
  }

  @JsonCreator
  public Message(
      @JsonProperty("id") String id,
      @JsonProperty("content") String content,
      @JsonProperty("timestamp") LocalDateTime timestamp) {
    this.id = id;
    this.content = content;
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    return "Message{"
        + "id='"
        + id
        + '\''
        + ", content='"
        + content
        + '\''
        + ", timestamp="
        + timestamp
        + '}';
  }
}
