package com.leadsphere.crm.patterns;

public class App {

  public static void main(String[] args) throws Exception {

    var ballItem = new BallItem();
    var ballThread = new BallThread();

    ballItem.setTwin(ballThread);
    ballThread.setTwin(ballItem);

    ballThread.start();

    waiting();

    ballItem.click();

    waiting();

    ballItem.click();

    waiting();

    // exit
    ballThread.stopMe();
  }

  private static void waiting() throws Exception {
    Thread.sleep(750);
  }
}
