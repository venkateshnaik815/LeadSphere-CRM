package com.leadsphere.crm.patterns;

import java.util.stream.LongStream;

public final class App {

  private App() {}

  public static void main(String[] args) {
    // Create an array of 10 million numbers: [1, 2, 3, ..., 10_000_000]
    long[] numbers = LongStream.rangeClosed(1, 10_000_000).toArray();

    // Calculate sum using Fork/Join
    ForkJoinSumCalculator calculator = new ForkJoinSumCalculator();
    long startTime = System.currentTimeMillis();
    long result = calculator.calculateSum(numbers);
    long endTime = System.currentTimeMillis();

    // The expected sum of 1 to N is N*(N+1)/2
    long expected = 10_000_000L * 10_000_001L / 2;

    System.out.println("Fork/Join sum: " + result);
    System.out.println("Expected sum:  " + expected);
    System.out.println("Correct: " + (result == expected));
    System.out.println("Time taken: " + (endTime - startTime) + " ms");
    System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
  }
}
