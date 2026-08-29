package com.leadsphere.crm.patterns;

import com.iluwatar.model.view.intent.actions.AdditionCalculatorAction;
import com.iluwatar.model.view.intent.actions.CalculatorAction;
import com.iluwatar.model.view.intent.actions.DivisionCalculatorAction;
import com.iluwatar.model.view.intent.actions.MultiplicationCalculatorAction;
import com.iluwatar.model.view.intent.actions.SetVariableCalculatorAction;
import com.iluwatar.model.view.intent.actions.SubtractionCalculatorAction;

public final class CalculatorViewModel {

  private CalculatorModel model = new CalculatorModel(0.0, 0.0);

  void handleAction(final CalculatorAction action) {
    switch (action.tag()) {
      case AdditionCalculatorAction.ADDITION -> add();
      case SubtractionCalculatorAction.SUBTRACTION -> subtract();
      case MultiplicationCalculatorAction.MULTIPLICATION -> multiply();
      case DivisionCalculatorAction.DIVISION -> divide();
      case SetVariableCalculatorAction.SET_VARIABLE -> {
        SetVariableCalculatorAction setVariableAction = (SetVariableCalculatorAction) action;
        setVariable(setVariableAction.getVariable());
      }
      default -> throw new IllegalArgumentException("Unknown tag");
    }
  }

  public CalculatorModel getCalculatorModel() {
    return model;
  }

  private void setVariable(final Double variable) {
    model = new CalculatorModel(variable, model.getOutput());
  }

  private void add() {
    model = new CalculatorModel(model.getVariable(), model.getOutput() + model.getVariable());
  }

  private void subtract() {
    model = new CalculatorModel(model.getVariable(), model.getOutput() - model.getVariable());
  }

  private void multiply() {
    model = new CalculatorModel(model.getVariable(), model.getOutput() * model.getVariable());
  }

  private void divide() {
    model = new CalculatorModel(model.getVariable(), model.getOutput() / model.getVariable());
  }
}
