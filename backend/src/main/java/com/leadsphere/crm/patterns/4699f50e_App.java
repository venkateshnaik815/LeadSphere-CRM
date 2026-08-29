package com.leadsphere.crm.patterns;

public class App {

  public static void main(String[] args) {
    var thief = new HalflingThief(new HitAndRunMethod());
    thief.steal();
    thief.changeMethod(new SubtleMethod());
    thief.steal();
  }
}
