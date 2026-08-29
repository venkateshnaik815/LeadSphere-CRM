package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    // Simulate concurrent requests from multiple users
    for (int i = 1; i <= 5; i++) {
      // Simulate tokens for different users
      String token = "token::" + (i % 3 + 1); // 3 distinct users

      new Thread(
              () -> {
                // Simulate request processing flow
                RequestHandler handler = new RequestHandler(token);
                handler.process();
              })
          .start();

      // Slightly stagger request times
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        LOGGER.warn("Sleep interrupted", e);
      }
    }
  }
}
