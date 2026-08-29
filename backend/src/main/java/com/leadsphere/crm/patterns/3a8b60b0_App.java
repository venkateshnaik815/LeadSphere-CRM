package com.leadsphere.crm.patterns;

public class App {
  public static void main(final String[] args) {
    final var nums = new int[] {1, 2, 3, 4, 5};
    // Before migration
    final var oldSystem = new OldArithmetic(new OldSource());
    oldSystem.sum(nums);
    oldSystem.mul(nums);
    // In process of migration
    final var halfSystem = new HalfArithmetic(new HalfSource(), new OldSource());
    halfSystem.sum(nums);
    halfSystem.mul(nums);
    halfSystem.ifHasZero(nums);
    // After migration
    final var newSystem = new NewArithmetic(new NewSource());
    newSystem.sum(nums);
    newSystem.mul(nums);
    newSystem.ifHasZero(nums);
  }
}
