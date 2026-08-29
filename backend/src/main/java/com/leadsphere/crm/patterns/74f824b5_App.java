package com.leadsphere.crm.patterns;

import java.util.concurrent.CountDownLatch;

public class App {

  protected static CountDownLatch latch;

  public static void main(String[] args) throws InterruptedException {

    Subscriber sub = new Subscriber();
    // slow publisher emit 15 numbers with a delay of 200 milliseconds
    Publisher.publish(1, 17, 200).subscribe(sub);

    latch = new CountDownLatch(1);
    latch.await();

    sub = new Subscriber();
    // fast publisher emit 15 numbers with a delay of 1 millisecond
    Publisher.publish(1, 17, 1).subscribe(sub);

    latch = new CountDownLatch(1);
    latch.await();
  }
}
