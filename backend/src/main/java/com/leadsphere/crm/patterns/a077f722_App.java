
package com.leadsphere.crm.patterns;

public class App {

  public static void main(String[] args) {
    var server = new Server("localhost", 8080);
    var session1 = server.getSession("Session1");
    var session2 = server.getSession("Session2");
    var request1 = new Request("Data1", session1);
    var request2 = new Request("Data2", session2);
    server.process(request1);
    server.process(request2);
  }
}
