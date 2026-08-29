package com.leadsphere.crm.patterns;

import java.util.ArrayList;
import java.util.List;

public class LoadBalancer {
  private static final List<Server> SERVERS = new ArrayList<>();
  private static int lastServedId;

  static {
    var id = 0;
    for (var port : new int[] {8080, 8081, 8082, 8083, 8084}) {
      SERVERS.add(new Server("localhost", port, ++id));
    }
  }

  public final void addServer(Server server) {
    synchronized (SERVERS) {
      SERVERS.add(server);
    }
  }

  public final int getNoOfServers() {
    return SERVERS.size();
  }

  public int getLastServedId() {
    return lastServedId;
  }

  public synchronized void serverRequest(Request request) {
    if (lastServedId >= SERVERS.size()) {
      lastServedId = 0;
    }
    var server = SERVERS.get(lastServedId++);
    server.serve(request);
  }
}
