package com.leadsphere.crm.patterns;

import com.google.common.base.Joiner;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashSet;
import java.util.PrimitiveIterator;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class LotteryNumbers {

  private final Set<Integer> numbers;

  public static final int MIN_NUMBER = 1;
  public static final int MAX_NUMBER = 20;
  public static final int NUM_NUMBERS = 4;

  private LotteryNumbers() {
    numbers = new HashSet<>();
    generateRandomNumbers();
  }

  private LotteryNumbers(Set<Integer> givenNumbers) {
    numbers = new HashSet<>();
    numbers.addAll(givenNumbers);
  }

  public static LotteryNumbers createRandom() {
    return new LotteryNumbers();
  }

  public static LotteryNumbers create(Set<Integer> givenNumbers) {
    return new LotteryNumbers(givenNumbers);
  }

  public Set<Integer> getNumbers() {
    return Collections.unmodifiableSet(numbers);
  }

  public String getNumbersAsString() {
    return Joiner.on(',').join(numbers);
  }

  private void generateRandomNumbers() {
    numbers.clear();
    var generator = new RandomNumberGenerator(MIN_NUMBER, MAX_NUMBER);
    while (numbers.size() < NUM_NUMBERS) {
      var num = generator.nextInt();
      numbers.add(num);
    }
  }

  private static class RandomNumberGenerator {

    private final PrimitiveIterator.OfInt randomIterator;

    public RandomNumberGenerator(int min, int max) {
      randomIterator = new SecureRandom().ints(min, max + 1).iterator();
    }

    public int nextInt() {
      return randomIterator.nextInt();
    }
  }
}
