package com.leadsphere.crm.patterns;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FanOutFanIn {

  public static Long fanOutFanIn(
      final List<SquareNumberRequest> requests, final Consumer consumer) {

    ExecutorService service = Executors.newFixedThreadPool(requests.size());

    // fanning out
    List<CompletableFuture<Void>> futures =
        requests.stream()
            .map(
                request ->
                    CompletableFuture.runAsync(() -> request.delayedSquaring(consumer), service))
            .toList();

    CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

    return consumer.getSumOfSquaredNumbers().get();
  }
}
