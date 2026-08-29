package com.leadsphere.crm.patterns;

import java.util.Collection;
import java.util.Map;

public abstract class Point<T> {

  public int coordinateX;
  public int coordinateY;
  public final int id;

  Point(int x, int y, int id) {
    this.coordinateX = x;
    this.coordinateY = y;
    this.id = id;
  }

  abstract void move();

  abstract boolean touches(T obj);

  abstract void handleCollision(Collection<? extends Point> toCheck, Map<Integer, T> all);
}
