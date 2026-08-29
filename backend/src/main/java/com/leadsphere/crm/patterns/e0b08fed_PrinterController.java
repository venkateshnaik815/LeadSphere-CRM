package com.leadsphere.crm.patterns;

public class PrinterController implements Printer {

  private final Printer printer;

  public PrinterController(Printer printer) {
    this.printer = printer;
  }

  @Override
  public void print(String message) {
    printer.print(message);
  }
}
