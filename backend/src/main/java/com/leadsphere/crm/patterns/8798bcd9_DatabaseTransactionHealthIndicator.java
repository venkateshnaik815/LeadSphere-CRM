package com.leadsphere.crm.patterns;

import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Setter
@Getter
public class DatabaseTransactionHealthIndicator implements HealthIndicator {

  private final HealthCheckRepository healthCheckRepository;

  private final AsynchronousHealthChecker asynchronousHealthChecker;

  private final RetryTemplate retryTemplate;

  @Value("${health.check.timeout:10}")
  private long timeoutInSeconds;

  @Override
  public Health health() {
    LOGGER.info("Calling performCheck with timeout {}", timeoutInSeconds);
    Supplier<Health> dbTransactionCheck =
        () -> {
          try {
            healthCheckRepository.performTestTransaction();
            return Health.up().build();
          } catch (Exception e) {
            LOGGER.error("Database transaction health check failed", e);
            return Health.down(e).build();
          }
        };
    try {
      return asynchronousHealthChecker.performCheck(dbTransactionCheck, timeoutInSeconds).get();
    } catch (InterruptedException | ExecutionException e) {
      LOGGER.error("Database transaction health check timed out or was interrupted", e);
      Thread.currentThread().interrupt();
      return Health.down(e).build();
    }
  }
}
