package com.leadsphere.crm.patterns;

public class App {

  public static void main(String[] args) {
    var queue = new SimpleMessageQueue(10000);

    final var producer = new Producer("PRODUCER_1", queue);
    final var consumer = new Consumer("CONSUMER_1", queue);

    new Thread(consumer::consume).start();

    new Thread(
            () -> {
              producer.send("hand shake");
              producer.send("some very important information");
              producer.send("bye!");
              producer.stop();
            })
        .start();
  }
}
