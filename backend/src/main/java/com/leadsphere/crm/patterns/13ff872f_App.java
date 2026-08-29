package com.leadsphere.crm.patterns;

public class App {

  public static void main(String[] args) {
    var conUnix = new ConfigureForUnixVisitor();
    var conDos = new ConfigureForDosVisitor();

    var zoom = new Zoom();
    var hayes = new Hayes();

    hayes.accept(conDos); // Hayes modem with Dos configurator
    zoom.accept(conDos); // Zoom modem with Dos configurator
    hayes.accept(conUnix); // Hayes modem with Unix configurator
    zoom.accept(conUnix); // Zoom modem with Unix configurator
  }
}
