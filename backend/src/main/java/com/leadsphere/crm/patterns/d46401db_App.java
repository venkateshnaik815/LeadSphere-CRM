package com.leadsphere.crm.patterns;

import java.util.concurrent.Callable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  private static final String ROCKET_LAUNCH_LOG_PATTERN = "Space rocket <%s> launched successfully";

  public static void main(String[] args) throws Exception {
    // construct a new executor that will run async tasks
    var executor = new ThreadAsyncExecutor();

    // start few async tasks with varying processing times, two last with callback handlers
    final var asyncResult1 = executor.startProcess(lazyval(10, 500));
    final var asyncResult2 = executor.startProcess(lazyval("test", 300));
    final var asyncResult3 = executor.startProcess(lazyval(50L, 700));
    final var asyncResult4 =
        executor.startProcess(lazyval(20, 400), callback("Deploying lunar rover"));
    final var asyncResult5 =
        executor.startProcess(lazyval("callback", 600), callback("Deploying lunar rover"));

    // emulate processing in the current thread while async tasks are running in their own threads
    Thread.sleep(350); // Oh boy, we are working hard here
    log("Mission command is sipping coffee");

    // wait for completion of the tasks
    final var result1 = executor.endProcess(asyncResult1);
    final var result2 = executor.endProcess(asyncResult2);
    final var result3 = executor.endProcess(asyncResult3);
    asyncResult4.await();
    asyncResult5.await();

    // log the results of the tasks, callbacks log immediately when complete
    log(String.format(ROCKET_LAUNCH_LOG_PATTERN, result1));
    log(String.format(ROCKET_LAUNCH_LOG_PATTERN, result2));
    log(String.format(ROCKET_LAUNCH_LOG_PATTERN, result3));
  }

  private static <T> Callable<T> lazyval(T value, long delayMillis) {
    return () -> {
      Thread.sleep(delayMillis);
      log(String.format(ROCKET_LAUNCH_LOG_PATTERN, value));
      return value;
    };
  }

  private static <T> AsyncCallback<T> callback(String name) {
    return new AsyncCallback<>() {
      @Override
      public void onComplete(T value) {
        log(name + " <" + value + ">");
      }

      @Override
      public void onError(Exception ex) {
        log(name + " failed: " + ex.getMessage());
      }
    };
  }

  private static void log(String msg) {
    LOGGER.info(msg);
  }
}
