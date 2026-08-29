package com.leadsphere.crm.patterns;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Bubble extends Point<Bubble> {
  private static final SecureRandom RANDOM = new SecureRandom();

  final int radius;

  Bubble(int x, int y, int id, int radius) {
    super(x, y, id);
    this.radius = radius;
  }

  void move() {
    // moves by 1 unit in either direction
    this.coordinateX += RANDOM.nextInt(3) - 1;
    this.coordinateY += RANDOM.nextInt(3) - 1;
  }

  boolean touches(Bubble b) {
    // distance between them is greater than sum of radii (both sides of equation squared)
    return (this.coordinateX - b.coordinateX) * (this.coordinateX - b.coordinateX)
            + (this.coordinateY - b.coordinateY) * (this.coordinateY - b.coordinateY)
        <= (this.radius + b.radius) * (this.radius + b.radius);
  }

  void pop(Map<Integer, Bubble> allBubbles) {
    LOGGER.info("Bubble {} popped at ({},{})!", this.id, this.coordinateX, this.coordinateY);
    allBubbles.remove(this.id);
  }

  void handleCollision(Collection<? extends Point> toCheck, Map<Integer, Bubble> allBubbles) {
    var toBePopped = false; // if any other bubble collides with it, made true
    for (var point : toCheck) {
      var otherId = point.id;
      if (allBubbles.get(otherId) != null // the bubble hasn't been popped yet
          && this.id != otherId // the two bubbles are not the same
          && this.touches(allBubbles.get(otherId))) { // the bubbles touch
        allBubbles.get(otherId).pop(allBubbles);
        toBePopped = true;
      }
    }
    if (toBePopped) {
      this.pop(allBubbles);
    }
  }
}
