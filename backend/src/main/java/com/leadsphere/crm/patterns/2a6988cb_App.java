package com.leadsphere.crm.patterns;

public class App {
  public static void main(String[] args) {
    var loadBalancer1 = new LoadBalancer();
    var loadBalancer2 = new LoadBalancer();
    loadBalancer1.serverRequest(new Request("Hello"));
    loadBalancer2.serverRequest(new Request("Hello World"));
  }
}
