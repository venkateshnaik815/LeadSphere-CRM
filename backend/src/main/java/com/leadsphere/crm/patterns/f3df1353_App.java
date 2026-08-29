package com.leadsphere.crm.patterns;

import java.util.LinkedList;
import java.util.Queue;

public class App {
  static PrinterQueue printerQueue = PrinterQueue.getInstance();

  public static void main(String[] args) {
    printerQueue.addPrinterItem(new PrinterItem(PaperSizes.A4, 5, false, false));
    printerQueue.addPrinterItem(new PrinterItem(PaperSizes.A3, 2, false, false));
    printerQueue.addPrinterItem(new PrinterItem(PaperSizes.A2, 5, false, false));

    var result = new LinkedList<PrinterItem>();

    addValidA4Papers(result);
    addValidA3Papers(result);
    addValidA2Papers(result);
  }

  public static void addValidA4Papers(Queue<PrinterItem> printerItemsCollection) {
    for (PrinterItem nextItem : printerQueue.getPrinterQueue()) {
      if (nextItem.paperSize.equals(PaperSizes.A4)) {
        var isColouredAndSingleSided = nextItem.isColour && !nextItem.isDoubleSided;
        if (isColouredAndSingleSided || !nextItem.isColour) {
          printerItemsCollection.add(nextItem);
        }
      }
    }
  }

  public static void addValidA3Papers(Queue<PrinterItem> printerItemsCollection) {
    for (PrinterItem nextItem : printerQueue.getPrinterQueue()) {
      if (nextItem.paperSize.equals(PaperSizes.A3)) {

        // Encoding the policy into a Boolean: the A3 paper cannot be coloured and double-sided at
        // the same time
        var isNotColouredAndSingleSided = !nextItem.isColour && !nextItem.isDoubleSided;
        if (isNotColouredAndSingleSided) {
          printerItemsCollection.add(nextItem);
        }
      }
    }
  }

  public static void addValidA2Papers(Queue<PrinterItem> printerItemsCollection) {
    for (PrinterItem nextItem : printerQueue.getPrinterQueue()) {
      if (nextItem.paperSize.equals(PaperSizes.A2)) {

        // Encoding the policy into a Boolean: the A2 paper must be single page, single-sided, and
        // non-coloured.
        var isNotColouredSingleSidedAndOnePage =
            nextItem.pageCount == 1 && !nextItem.isDoubleSided && !nextItem.isColour;
        if (isNotColouredSingleSidedAndOnePage) {
          printerItemsCollection.add(nextItem);
        }
      }
    }
  }
}
