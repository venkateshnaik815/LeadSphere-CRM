package com.leadsphere.crm.patterns;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public App(String message, String signal) {
    var console = new CompositeEntity();
    console.init();
    console.setData(message, signal);
    Arrays.stream(console.getData()).forEach(LOGGER::info);
    console.setData("Danger", "Red Light");
    Arrays.stream(console.getData()).forEach(LOGGER::info);
  }

  public static void main(String[] args) {

    new App("No Danger", "Green Light");
  }
}
