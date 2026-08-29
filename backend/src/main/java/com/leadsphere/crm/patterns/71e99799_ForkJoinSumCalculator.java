package com.leadsphere.crm.patterns;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinSumCalculator {

  private final ForkJoinPool pool;

  public ForkJoinSumCalculator() {
    this.pool = ForkJoinPool.commonPool();
  }

  public ForkJoinSumCalculator(int parallelism) {
    this.pool = new ForkJoinPool(parallelism);
  }

  public long calculateSum(long[] numbers) {
    if (numbers == null || numbers.length == 0) {
      return 0;
    }
    SumTask task = new SumTask(numbers, 0, numbers.length);
    return pool.invoke(task);
  }
}
