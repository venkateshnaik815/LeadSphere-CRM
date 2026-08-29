package com.leadsphere.crm.patterns;

public class App {

  public static void main(String[] args) {
    // stew is mutable
    var stew = new Stew(1, 2, 3, 4);
    stew.mix();
    stew.taste();
    stew.mix();

    // immutable stew protected with Private Class Data pattern
    var immutableStew = new ImmutableStew(2, 4, 3, 6);
    immutableStew.mix();
  }
}
