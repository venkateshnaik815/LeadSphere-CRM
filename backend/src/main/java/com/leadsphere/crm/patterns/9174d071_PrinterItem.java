package com.leadsphere.crm.patterns;

import java.util.Objects;

public class PrinterItem {
  PaperSizes paperSize;
  int pageCount;
  boolean isDoubleSided;
  boolean isColour;

  public PrinterItem(PaperSizes paperSize, int pageCount, boolean isDoubleSided, boolean isColour) {
    if (!Objects.isNull(paperSize)) {
      this.paperSize = paperSize;
    } else {
      throw new IllegalArgumentException();
    }

    if (pageCount > 0) {
      this.pageCount = pageCount;
    } else {
      throw new IllegalArgumentException();
    }

    this.isColour = isColour;
    this.isDoubleSided = isDoubleSided;
  }
}
