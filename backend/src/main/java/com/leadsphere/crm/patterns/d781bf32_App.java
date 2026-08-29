package com.leadsphere.crm.patterns;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  private static final String DEFAULT_URL =
      "https://raw.githubusercontent.com/iluwatar/java-design-patterns/master/promise/README.md";
  private final ExecutorService executor;
  private final CountDownLatch stopLatch;

  private App() {
    executor = Executors.newFixedThreadPool(2);
    stopLatch = new CountDownLatch(2);
  }

  public static void main(String[] args) throws InterruptedException {
    var app = new App();
    try {
      app.promiseUsage();
    } finally {
      app.stop();
    }
  }

  private void promiseUsage() {
    calculateLineCount();

    calculateLowestFrequencyChar();
  }

  private void calculateLowestFrequencyChar() {
    lowestFrequencyChar()
        .thenAccept(
            charFrequency -> {
              LOGGER.info("Char with lowest frequency is: {}", charFrequency);
              taskCompleted();
            });
  }

  private void calculateLineCount() {
    countLines()
        .thenAccept(
            count -> {
              LOGGER.info("Line count is: {}", count);
              taskCompleted();
            });
  }

  private Promise<Character> lowestFrequencyChar() {
    return characterFrequency().thenApply(Utility::lowestFrequencyChar);
  }

  private Promise<Map<Character, Long>> characterFrequency() {
    return download(DEFAULT_URL).thenApply(Utility::characterFrequency);
  }

  private Promise<Integer> countLines() {
    return download(DEFAULT_URL).thenApply(Utility::countLines);
  }

  private Promise<String> download(String urlString) {
    return new Promise<String>()
        .fulfillInAsync(() -> Utility.downloadFile(urlString), executor)
        .onError(
            throwable -> {
              LOGGER.error("An error occurred: ", throwable);
              taskCompleted();
            });
  }

  private void stop() throws InterruptedException {
    stopLatch.await();
    executor.shutdownNow();
  }

  private void taskCompleted() {
    stopLatch.countDown();
  }
}
