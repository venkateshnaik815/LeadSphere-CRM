package com.leadsphere.crm.patterns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Rectangle {

  private final int left;
  private final int top;
  private final int right;
  private final int bottom;

  boolean intersectsWith(Rectangle r) {
    return !(r.getLeft() > getRight()
        || r.getRight() < getLeft()
        || r.getTop() > getBottom()
        || r.getBottom() < getTop());
  }

  @Override
  public String toString() {
    return String.format("[%d,%d,%d,%d]", getLeft(), getTop(), getRight(), getBottom());
  }
}
