package com.leadsphere.crm.patterns;

import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  static void noSpatialPartition(int numOfMovements, Map<Integer, Bubble> bubbles) {
    // all bubbles have to be checked for collision for all bubbles
    var bubblesToCheck = bubbles.values();

    // will run numOfMovement times or till all bubbles have popped
    while (numOfMovements > 0 && !bubbles.isEmpty()) {
      bubbles.forEach(
          (i, bubble) -> {
            // bubble moves, new position gets updated
            // and collisions are checked with all bubbles in bubblesToCheck
            bubble.move();
            bubbles.replace(i, bubble);
            bubble.handleCollision(bubblesToCheck, bubbles);
          });
      numOfMovements--;
    }
    // bubbles not popped
    bubbles.keySet().forEach(key -> LOGGER.info("Bubble {} not popped", key));
  }

  static void withSpatialPartition(
      int height, int width, int numOfMovements, Map<Integer, Bubble> bubbles) {
    // creating quadtree
    var rect = new Rect(width / 2D, height / 2D, width, height);
    var quadTree = new QuadTree(rect, 4);

    // will run numOfMovement times or till all bubbles have popped
    while (numOfMovements > 0 && !bubbles.isEmpty()) {
      // quadtree updated each time
      bubbles.values().forEach(quadTree::insert);
      bubbles.forEach(
          (i, bubble) -> {
            // bubble moves, new position gets updated, quadtree used to reduce computations
            bubble.move();
            bubbles.replace(i, bubble);
            var sp = new SpatialPartitionBubbles(bubbles, quadTree);
            sp.handleCollisionsUsingQt(bubble);
          });
      numOfMovements--;
    }
    // bubbles not popped
    bubbles.keySet().forEach(key -> LOGGER.info("Bubble {} not popped", key));
  }

  public static void main(String[] args) {
    var bubbles1 = new ConcurrentHashMap<Integer, Bubble>();
    var bubbles2 = new ConcurrentHashMap<Integer, Bubble>();
    var rand = new SecureRandom();
    for (int i = 0; i < 10000; i++) {
      var b = new Bubble(rand.nextInt(300), rand.nextInt(300), i, rand.nextInt(2) + 1);
      bubbles1.put(i, b);
      bubbles2.put(i, b);
      LOGGER.info(
          "Bubble {} with radius {} added at ({},{})", i, b.radius, b.coordinateX, b.coordinateY);
    }

    var start1 = System.currentTimeMillis();
    App.noSpatialPartition(20, bubbles1);
    var end1 = System.currentTimeMillis();
    var start2 = System.currentTimeMillis();
    App.withSpatialPartition(300, 300, 20, bubbles2);
    var end2 = System.currentTimeMillis();
    LOGGER.info("Without spatial partition takes {} ms", (end1 - start1));
    LOGGER.info("With spatial partition takes {} ms", (end2 - start2));
  }
}
