package com.leadsphere.crm.patterns;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomHealthIndicator implements HealthIndicator {

  private final AsynchronousHealthChecker healthChecker;
  private final CacheManager cacheManager;
  private final HealthCheckRepository healthCheckRepository;

  @Value("${health.check.timeout:10}")
  private long timeoutInSeconds;

  @Override
  @Cacheable(value = "health-check", unless = "#result.status == 'DOWN'")
  public Health health() {
    LOGGER.info("Performing health check");
    CompletableFuture<Health> healthFuture =
        healthChecker.performCheck(this::check, timeoutInSeconds);
    try {
      return healthFuture.get(timeoutInSeconds, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      LOGGER.error("Health check interrupted", e);
      throw new HealthCheckInterruptedException(e);
    } catch (Exception e) {
      LOGGER.error("Health check failed", e);
      return Health.down(e).build();
    }
  }

  private Health check() {
    Integer result = healthCheckRepository.checkHealth();
    boolean databaseIsUp = result != null && result == 1;
    LOGGER.info("Health check result: {}", databaseIsUp);
    return databaseIsUp
        ? Health.up().withDetail("database", "reachable").build()
        : Health.down().withDetail("database", "unreachable").build();
  }

  @Scheduled(fixedRateString = "${health.check.cache.evict.interval:60000}")
  public void evictHealthCache() {
    LOGGER.info("Evicting health check cache");
    try {
      Cache healthCheckCache = cacheManager.getCache("health-check");
      LOGGER.info("Health check cache: {}", healthCheckCache);
      if (healthCheckCache != null) {
        healthCheckCache.clear();
      }
    } catch (Exception e) {
      LOGGER.error("Failed to evict health check cache", e);
    }
  }
}
