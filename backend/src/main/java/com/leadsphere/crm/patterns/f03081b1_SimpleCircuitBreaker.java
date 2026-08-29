package com.leadsphere.crm.patterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleCircuitBreaker {
  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCircuitBreaker.class);

  private final int failureThreshold;
  private final long retryTimePeriodMs;
  private int failureCount = 0;
  private long lastFailureTime = 0;
  private State state = State.CLOSED;

  public enum State {
    CLOSED,
    OPEN,
    HALF_OPEN
  }

  public SimpleCircuitBreaker(int failureThreshold, long retryTimePeriodMs) {
    this.failureThreshold = failureThreshold;
    this.retryTimePeriodMs = retryTimePeriodMs;
  }

  public synchronized State getState() {
    evaluateState();
    return state;
  }

  private void evaluateState() {
    if (state == State.OPEN) {
      if (System.currentTimeMillis() - lastFailureTime > retryTimePeriodMs) {
        state = State.HALF_OPEN;
        LOGGER.info("Circuit Breaker transitioned to HALF_OPEN");
      }
    }
  }

  public synchronized void recordSuccess() {
    failureCount = 0;
    state = State.CLOSED;
    LOGGER.info("Circuit Breaker transitioned to CLOSED (success recorded)");
  }

  public synchronized void recordFailure() {
    failureCount++;
    lastFailureTime = System.currentTimeMillis();
    if (state == State.CLOSED && failureCount >= failureThreshold) {
      state = State.OPEN;
      LOGGER.warn("Circuit Breaker transitioned to OPEN (failure threshold reached)");
    } else if (state == State.HALF_OPEN) {
      state = State.OPEN;
      LOGGER.warn("Circuit Breaker transitioned to OPEN (failed during HALF_OPEN)");
    }
  }
}
