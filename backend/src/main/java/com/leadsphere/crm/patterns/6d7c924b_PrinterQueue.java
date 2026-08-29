package com.leadsphere.crm.patterns;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class PrinterQueue {

  static PrinterQueue currentInstance = null;
  private final Queue<PrinterItem> printerItemQueue;

  public static PrinterQueue getInstance() {
    if (Objects.isNull(currentInstance)) {
      currentInstance = new PrinterQueue();
    }
    return currentInstance;
  }

  public void emptyQueue() {
    currentInstance.getPrinterQueue().clear();
  }

  private PrinterQueue() {
    printerItemQueue = new LinkedList<>();
  }

  public Queue<PrinterItem> getPrinterQueue() {
    return currentInstance.printerItemQueue;
  }

  public void addPrinterItem(PrinterItem printerItem) {
    currentInstance.getPrinterQueue().add(printerItem);
  }
}
