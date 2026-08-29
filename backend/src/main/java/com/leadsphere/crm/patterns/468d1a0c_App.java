package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class App {
  private App() {}

  public static void main(final String[] args) {
    var view = new View();
    view.createView();
  }
}
