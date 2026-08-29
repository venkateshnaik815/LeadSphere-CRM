package com.leadsphere.crm.patterns;

import java.util.Map;

public abstract class SpatialPartitionGeneric<T> {

  Map<Integer, T> playerPositions;
  QuadTree quadTree;

  abstract void handleCollisionsUsingQt(T obj);
}
