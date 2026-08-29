package com.leadsphere.crm.patterns;

import java.util.ArrayList;
import java.util.Map;

public class SpatialPartitionBubbles extends SpatialPartitionGeneric<Bubble> {

  private final Map<Integer, Bubble> bubbles;
  private final QuadTree bubblesQuadTree;

  SpatialPartitionBubbles(Map<Integer, Bubble> bubbles, QuadTree bubblesQuadTree) {
    this.bubbles = bubbles;
    this.bubblesQuadTree = bubblesQuadTree;
  }

  void handleCollisionsUsingQt(Bubble b) {
    // finding points within area of a square drawn with centre same as
    // centre of bubble and length = radius of bubble
    var rect = new Rect(b.coordinateX, b.coordinateY, 2D * b.radius, 2D * b.radius);
    var quadTreeQueryResult = new ArrayList<Point>();
    this.bubblesQuadTree.query(rect, quadTreeQueryResult);
    // handling these collisions
    b.handleCollision(quadTreeQueryResult, this.bubbles);
  }
}
