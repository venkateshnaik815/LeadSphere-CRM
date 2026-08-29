package com.leadsphere.crm.patterns;

import com.iluwatar.model.view.intent.actions.AdditionCalculatorAction;
import com.iluwatar.model.view.intent.actions.DivisionCalculatorAction;
import com.iluwatar.model.view.intent.actions.MultiplicationCalculatorAction;
import com.iluwatar.model.view.intent.actions.SetVariableCalculatorAction;
import com.iluwatar.model.view.intent.actions.SubtractionCalculatorAction;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class CalculatorView {

  @Getter private final CalculatorViewModel viewModel;

  void displayTotal() {
    LOGGER.info("Total value = {}", viewModel.getCalculatorModel().getOutput().toString());
  }

  void add() {
    viewModel.handleAction(new AdditionCalculatorAction());
  }

  void subtract() {
    viewModel.handleAction(new SubtractionCalculatorAction());
  }

  void multiply() {
    viewModel.handleAction(new MultiplicationCalculatorAction());
  }

  void divide() {
    viewModel.handleAction(new DivisionCalculatorAction());
  }

  void setVariable(final Double value) {
    viewModel.handleAction(new SetVariableCalculatorAction(value));
  }
}
