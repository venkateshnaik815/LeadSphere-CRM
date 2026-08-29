package com.leadsphere.crm.patterns;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FallbackExecutor implements AutoCloseable {
  private static final Logger LOGGER = LoggerFactory.getLogger(FallbackExecutor.class);
  private final ExecutorService executorService;

  public FallbackExecutor() {
    this.executorService = Executors.newVirtualThreadPerTaskExecutor();
  }

  public String execute(
      RemoteService primary,
      RemoteService fallback,
      SimpleCircuitBreaker circuitBreaker,
      long timeoutMs) {

    // 1. Check Circuit Breaker
    if (circuitBreaker.getState() == SimpleCircuitBreaker.State.OPEN) {
      LOGGER.warn("Circuit is OPEN. Fast-failing and calling fallback service.");
      try {
        return fallback.execute();
      } catch (Exception ex) {
        LOGGER.error("Fallback service execution failed: {}", ex.getMessage());
        return "Fallback Error";
      }
    }

    // 2. Attempt service call with timeout
    Callable<String> task = primary::execute;
    Future<String> future = executorService.submit(task);

    try {
      String result = future.get(timeoutMs, TimeUnit.MILLISECONDS);
      circuitBreaker.recordSuccess();
      return result;
    } catch (TimeoutException e) {
      LOGGER.error("Service call timed out. Triggering fallback.");
      future.cancel(true); // Interrupt / cancel the task
      circuitBreaker.recordFailure();
      try {
        return fallback.execute();
      } catch (Exception ex) {
        LOGGER.error("Fallback service execution failed: {}", ex.getMessage());
        return "Fallback Error";
      }
    } catch (Exception e) {
      LOGGER.error("Service call failed with exception: {}. Triggering fallback.", e.getMessage());
      circuitBreaker.recordFailure();
      try {
        return fallback.execute();
      } catch (Exception ex) {
        LOGGER.error("Fallback service execution failed: {}", ex.getMessage());
        return "Fallback Error";
      }
    }
  }

  @Override
  public void close() {
    executorService.shutdown();
  }
}
