
package com.leadsphere.crm.patterns;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
public class Server {
  private String host;

  private int port;

  public Session getSession(String name) {
    return new Session(UUID.randomUUID().toString(), name);
  }

  public void process(Request request) {
    LOGGER.info(
        "Processing Request with client: "
            + request.getSession().getClientName()
            + " data: "
            + request.getData());
  }
}
