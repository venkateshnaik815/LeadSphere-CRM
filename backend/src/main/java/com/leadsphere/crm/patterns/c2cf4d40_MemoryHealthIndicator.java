package com.leadsphere.crm.patterns;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemoryHealthIndicator implements HealthIndicator {

  private final AsynchronousHealthChecker asynchronousHealthChecker;

  @Value("${health.check.timeout:10}")
  private long timeoutInSeconds;

  @Value("${health.check.memory.threshold:0.9}")
  private double memoryThreshold;

  public Health checkMemory() {
    Supplier<Health> memoryCheck =
        () -> {
          MemoryMXBean memoryMxBean = ManagementFactory.getMemoryMXBean();
          MemoryUsage heapMemoryUsage = memoryMxBean.getHeapMemoryUsage();
          long maxMemory = heapMemoryUsage.getMax();
          long usedMemory = heapMemoryUsage.getUsed();

          double memoryUsage = (double) usedMemory / maxMemory;
          String format = String.format("%.2f%% of %d max", memoryUsage * 100, maxMemory);

          if (memoryUsage < memoryThreshold) {
            LOGGER.info("Memory usage is below threshold: {}", format);
            return Health.up().withDetail("memory usage", format).build();
          } else {
            return Health.down().withDetail("memory usage", format).build();
          }
        };

    try {
      CompletableFuture<Health> future =
          asynchronousHealthChecker.performCheck(memoryCheck, timeoutInSeconds);
      return future.get();
    } catch (InterruptedException e) {
      LOGGER.error("Health check interrupted", e);
      Thread.currentThread().interrupt();
      return Health.down().withDetail("error", "Health check interrupted").build();
    } catch (ExecutionException e) {
      LOGGER.error("Health check failed", e);
      Throwable cause = e.getCause() == null ? e : e.getCause();
      return Health.down().withDetail("error", cause.toString()).build();
    }
  }

  @Override
  public Health health() {
    return checkMemory();
  }
}
