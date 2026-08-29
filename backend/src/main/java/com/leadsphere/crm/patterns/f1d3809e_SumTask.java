package com.leadsphere.crm.patterns;

import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Long> {

  private static final int THRESHOLD = 1000;

  private final long[] numbers;
  private final int start;
  private final int end;

  public SumTask(long[] numbers, int start, int end) {
    if (start > end) {
      throw new IllegalArgumentException(
          "start (" + start + ") must not be greater than end (" + end + ")");
    }
    this.numbers = numbers;
    this.start = start;
    this.end = end;
  }

  @Override
  protected Long compute() {
    int length = end - start;

    // BASE CASE: if the chunk is small enough, just sum directly
    if (length <= THRESHOLD) {
      return computeDirectly();
    }

    // FORK: split the task into two halves
    int mid = start + length / 2;

    // Create subtask for the left half
    SumTask leftTask = new SumTask(numbers, start, mid);

    // Create subtask for the right half
    SumTask rightTask = new SumTask(numbers, mid, end);

    // Fork the left task — it will run in a separate thread
    leftTask.fork();

    // Compute the right task in the current thread (no need to fork both)
    long rightResult = rightTask.compute();

    // JOIN: wait for the left task to finish and get its result
    long leftResult = leftTask.join();

    // Combine the results from both halves
    return leftResult + rightResult;
  }

  private long computeDirectly() {
    long sum = 0;
    for (int i = start; i < end; i++) {
      sum += numbers[i];
    }
    return sum;
  }
}
