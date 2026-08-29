package com.leadsphere.crm.patterns;

import jakarta.annotation.PreDestroy;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AsynchronousHealthChecker {

  private final ScheduledExecutorService healthCheckExecutor =
      Executors.newSingleThreadScheduledExecutor();

  private static final String HEALTH_CHECK_TIMEOUT_MESSAGE = "Health check timed out";
  private static final String HEALTH_CHECK_FAILED_MESSAGE = "Health check failed";

  public CompletableFuture<Health> performCheck(
      Supplier<Health> healthCheck, long timeoutInSeconds) {
    CompletableFuture<Health> future =
        CompletableFuture.supplyAsync(healthCheck, healthCheckExecutor);

    // Schedule a task to enforce the timeout
    healthCheckExecutor.schedule(
        () -> {
          if (!future.isDone()) {
            LOGGER.error(HEALTH_CHECK_TIMEOUT_MESSAGE);
            future.completeExceptionally(new TimeoutException(HEALTH_CHECK_TIMEOUT_MESSAGE));
          }
        },
        timeoutInSeconds,
        TimeUnit.SECONDS);

    return future.handle(
        (result, throwable) -> {
          if (throwable != null) {
            LOGGER.error(HEALTH_CHECK_FAILED_MESSAGE, throwable);
            // Check if the throwable is a TimeoutException or caused by a TimeoutException
            Throwable rootCause =
                throwable instanceof CompletionException ? throwable.getCause() : throwable;
            if (!(rootCause instanceof TimeoutException)) {
              LOGGER.error(HEALTH_CHECK_FAILED_MESSAGE, rootCause);
              return Health.down().withException(rootCause).build();
            } else {
              LOGGER.error(HEALTH_CHECK_TIMEOUT_MESSAGE, rootCause);
              // If it is a TimeoutException, rethrow it wrapped in a CompletionException
              throw new CompletionException(rootCause);
            }
          } else {
            return result;
          }
        });
  }

  private boolean awaitTerminationWithTimeout() throws InterruptedException {
    boolean isTerminationIncomplete = !healthCheckExecutor.awaitTermination(5, TimeUnit.SECONDS);
    LOGGER.info("Termination status: {}", isTerminationIncomplete);
    // Await termination and return true if termination is incomplete (timeout elapsed)
    return isTerminationIncomplete;
  }

  @PreDestroy
  public void shutdown() {
    try {
      // Wait a while for existing tasks to terminate
      if (awaitTerminationWithTimeout()) {
        LOGGER.info("Health check executor did not terminate in time");
        // Attempt to cancel currently executing tasks
        healthCheckExecutor.shutdownNow();
        // Wait again for tasks to respond to being cancelled
        if (awaitTerminationWithTimeout()) {
          LOGGER.error("Health check executor did not terminate");
        }
      }
    } catch (InterruptedException ie) {
      // Preserve interrupt status
      Thread.currentThread().interrupt();
      // (Re-)Cancel if current thread also interrupted
      healthCheckExecutor.shutdownNow();
      // Log the stack trace for interrupted exception
      LOGGER.error("Shutdown of the health check executor was interrupted", ie);
    }
  }
}
