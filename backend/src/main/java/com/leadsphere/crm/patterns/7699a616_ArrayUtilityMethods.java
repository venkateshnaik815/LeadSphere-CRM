package com.leadsphere.crm.patterns;

import java.security.SecureRandom;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArrayUtilityMethods {

  private static final SecureRandom RANDOM = new SecureRandom();

  public static boolean arraysSame(int[] a1, int[] a2) {
    // compares if 2 arrays have the same value
    if (a1.length != a2.length) {
      return false;
    } else {
      var answer = false;
      for (var i = 0; i < a1.length; i++) {
        if (a1[i] == a2[i]) {
          answer = true;
        } else {
          answer = false;
          break;
        }
      }
      return answer;
    }
  }

  public static boolean matricesSame(int[][] m1, int[][] m2) {
    if (m1.length != m2.length) {
      return false;
    } else {
      var answer = false;
      for (var i = 0; i < m1.length; i++) {
        if (arraysSame(m1[i], m2[i])) {
          answer = true;
        } else {
          answer = false;
          break;
        }
      }
      return answer;
    }
  }

  public static int[][] createRandomIntMatrix(int rows, int columns) {
    var matrix = new int[rows][columns];
    for (var i = 0; i < rows; i++) {
      for (var j = 0; j < columns; j++) {
        // filling cells in matrix
        matrix[i][j] = RANDOM.nextInt(10);
      }
    }
    return matrix;
  }

  public static void printMatrix(int[][] matrix) {
    // prints out int[][]
    for (var ints : matrix) {
      for (var j = 0; j < matrix[0].length; j++) {
        LOGGER.info(ints[j] + " ");
      }
      LOGGER.info("");
    }
  }
}
