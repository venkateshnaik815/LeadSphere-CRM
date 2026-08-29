package com.leadsphere.crm.patterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    try (var executor = new FallbackExecutor()) {
      var healthyPrimary = new PrimaryService("Healthy data from primary service", 10, false);
      var failingPrimary = new PrimaryService("Failing service", 0, true);
      var slowPrimary = new PrimaryService("Slow response from primary service", 500, false);
      var fallback = new FallbackService("Fallback degraded/cached response");

      // Failure threshold is 2 failures, retry time period is 1 second (1000ms)
      var circuitBreaker = new SimpleCircuitBreaker(2, 1000);

      // Scenario 1: Healthy primary service call
      LOGGER.info("Scenario 1: Executing request to healthy primary service...");
      String response1 = executor.execute(healthyPrimary, fallback, circuitBreaker, 100);
      LOGGER.info("Response received: {}", response1);
      LOGGER.info("Circuit Breaker State: {}\n", circuitBreaker.getState());

      // Scenario 2: Failing primary service call (fails and increments failure count to 1)
      LOGGER.info("Scenario 2: Executing request to failing primary service (throws exception)...");
      String response2 = executor.execute(failingPrimary, fallback, circuitBreaker, 100);
      LOGGER.info("Response received: {}", response2);
      LOGGER.info("Circuit Breaker State: {}\n", circuitBreaker.getState());

      // Scenario 3: Slow primary service call (times out and increments failure count to 2,
      // tripping breaker)
      LOGGER.info("Scenario 3: Executing request to slow primary service (triggers timeout)...");
      String response3 = executor.execute(slowPrimary, fallback, circuitBreaker, 100);
      LOGGER.info("Response received: {}", response3);
      LOGGER.info("Circuit Breaker State: {}\n", circuitBreaker.getState());

      // Scenario 4: Fast failing when circuit is OPEN
      LOGGER.info("Scenario 4: Executing request while Circuit Breaker is OPEN...");
      String response4 = executor.execute(healthyPrimary, fallback, circuitBreaker, 100);
      LOGGER.info("Response received: {}", response4);
      LOGGER.info("Circuit Breaker State: {}\n", circuitBreaker.getState());

      // Scenario 5: Recovery from OPEN state
      LOGGER.info("Scenario 5: Waiting for retry period to elapse...");
      try {
        Thread.sleep(1100); // Wait longer than retryTimePeriodMs (1000ms)
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      LOGGER.info(
          "Circuit Breaker State (should be HALF_OPEN on next check): {}",
          circuitBreaker.getState());
      LOGGER.info("Executing request to healthy primary service to reset breaker...");
      String response5 = executor.execute(healthyPrimary, fallback, circuitBreaker, 100);
      LOGGER.info("Response received: {}", response5);
      LOGGER.info("Circuit Breaker State (should be CLOSED): {}\n", circuitBreaker.getState());
    }
  }
}
