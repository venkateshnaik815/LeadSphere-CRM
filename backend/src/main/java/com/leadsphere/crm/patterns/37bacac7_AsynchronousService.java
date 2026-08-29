package com.leadsphere.crm.patterns;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AsynchronousService {
  private final ExecutorService service;

  public AsynchronousService(BlockingQueue<Runnable> workQueue) {
    service = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, workQueue);
  }

  public <T> void execute(final AsyncTask<T> task) {
    try {
      // some small tasks such as validation can be performed here.
      task.onPreCall();
    } catch (Exception e) {
      task.onError(e);
      return;
    }

    service.submit(
        new FutureTask<>(task) {
          @Override
          protected void done() {
            super.done();
            try {
              task.onPostCall(get());
            } catch (InterruptedException e) {
              // should not occur
            } catch (ExecutionException e) {
              task.onError(e.getCause());
            }
          }
        });
  }

  public void close() {
    service.shutdown();
    try {
      service.awaitTermination(10, TimeUnit.SECONDS);
    } catch (InterruptedException ie) {
      LOGGER.error("Error waiting for executor service shutdown!");
    }
  }
}
