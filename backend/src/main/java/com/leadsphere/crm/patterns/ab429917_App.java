package com.leadsphere.crm.patterns;

public final class App {

  private App() {}

  public static void main(final String[] args) {
    // The captain can only operate rowing boats but with adapter he is able to
    // use fishing boats as well
    var captain = new Captain(new FishingBoatAdapter());
    captain.row();
  }
}
