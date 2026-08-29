package com.leadsphere.crm.patterns;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class Server {

  public final String host;
  public final int port;
  public final int id;

  public Server(String host, int port, int id) {
    this.host = host;
    this.port = port;
    this.id = id;
  }

  public void serve(Request request) {
    LOGGER.info(
        "Server ID {} associated to host : {} and port {}. Processed request with value {}",
        id,
        host,
        port,
        request.value());
  }
}
