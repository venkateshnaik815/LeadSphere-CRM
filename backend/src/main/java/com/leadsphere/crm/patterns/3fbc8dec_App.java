package com.leadsphere.crm.patterns;

public final class App {

  private static final double RANDOM_VARIABLE = 10.0;

  public static void main(final String[] args) {
    // create model, view and controller

    // initialize calculator view, output and variable = 0
    var view = new CalculatorView(new CalculatorViewModel());
    var variable1 = RANDOM_VARIABLE;

    // calculator variable = RANDOM_VARIABLE -> 10.0
    view.setVariable(variable1);

    // add calculator variable to output -> calculator output = 10.0
    view.add();
    view.displayTotal(); // display output

    variable1 = 2.0;
    view.setVariable(variable1); // calculator variable = 2.0

    // subtract calculator variable from output -> calculator output = 8
    view.subtract();

    // divide calculator output by variable -> calculator output = 4.0
    view.divide();

    // multiply calculator output by variable -> calculator output = 8.0
    view.multiply();
    view.displayTotal();
  }

  private App() {}
}
