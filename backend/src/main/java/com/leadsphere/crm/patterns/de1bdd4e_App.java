package com.leadsphere.crm.patterns;

import java.util.Stack;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {

    // the halfling kids are learning some basic math at school
    // define the math string we want to parse
    final var tokenString = "4 3 2 - 1 + *";

    // the stack holds the parsed expressions
    var stack = new Stack<Expression>();

    // tokenize the string and go through them one by one
    var tokenList = tokenString.split(" ");
    for (var s : tokenList) {
      if (isOperator(s)) {
        // when an operator is encountered we expect that the numbers can be popped from the top of
        // the stack
        var rightExpression = stack.pop();
        var leftExpression = stack.pop();
        LOGGER.info(
            "popped from stack left: {} right: {}",
            leftExpression.interpret(),
            rightExpression.interpret());
        var operator = getOperatorInstance(s, leftExpression, rightExpression);
        LOGGER.info("operator: {}", operator);
        var result = operator.interpret();
        // the operation result is pushed on top of the stack
        var resultExpression = new NumberExpression(result);
        stack.push(resultExpression);
        LOGGER.info("push result to stack: {}", resultExpression.interpret());
      } else {
        // numbers are pushed on top of the stack
        var i = new NumberExpression(s);
        stack.push(i);
        LOGGER.info("push to stack: {}", i.interpret());
      }
    }
    // in the end, the final result lies on top of the stack
    LOGGER.info("result: {}", stack.pop().interpret());
  }

  public static boolean isOperator(String s) {
    return s.equals("+") || s.equals("-") || s.equals("*");
  }

  public static Expression getOperatorInstance(String s, Expression left, Expression right) {
    return switch (s) {
      case "+" -> new PlusExpression(left, right);
      case "-" -> new MinusExpression(left, right);
      default -> new MultiplyExpression(left, right);
    };
  }
}
