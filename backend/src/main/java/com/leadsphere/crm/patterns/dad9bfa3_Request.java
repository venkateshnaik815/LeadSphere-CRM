package com.leadsphere.crm.patterns;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Request {
  enum Status {
    PENDING,
    STARTED,
    COMPLETED
  }

  @Id private UUID uuid;
  private Status status;

  public Request(UUID uuid) {
    this(uuid, Status.PENDING);
  }

  public Request(UUID uuid, Status status) {
    this.uuid = uuid;
    this.status = status;
  }
}
