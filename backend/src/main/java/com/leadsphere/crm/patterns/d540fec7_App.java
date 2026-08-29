package com.leadsphere.crm.patterns;

import java.util.concurrent.LinkedBlockingQueue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    var service = new AsynchronousService(new LinkedBlockingQueue<>());
    service.execute(new ArithmeticSumTask(1000));

    service.execute(new ArithmeticSumTask(500));
    service.execute(new ArithmeticSumTask(2000));
    service.execute(new ArithmeticSumTask(1));

    service.close();
  }

  static class ArithmeticSumTask implements AsyncTask<Long> {
    private final long numberOfElements;

    public ArithmeticSumTask(long numberOfElements) {
      this.numberOfElements = numberOfElements;
    }

    @Override
    public Long call() {
      return ap(numberOfElements);
    }

    @Override
    public void onPreCall() {
      if (numberOfElements < 0) {
        throw new IllegalArgumentException("n is less than 0");
      }
    }

    @Override
    public void onPostCall(Long result) {
      // Handle the result of computation
      LOGGER.info(result.toString());
    }

    @Override
    public void onError(Throwable throwable) {
      throw new IllegalStateException("Should not occur");
    }
  }

  private static long ap(long i) {
    try {
      Thread.sleep(i);
    } catch (InterruptedException e) {
      LOGGER.error("Exception caught.", e);
    }
    return i * (i + 1) / 2;
  }
}
