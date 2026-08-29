package com.leadsphere.crm.patterns;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

@Slf4j
public class Scene {

  private final Buffer[] frameBuffers;

  private int current;

  private int next;

  public Scene() {
    frameBuffers = new FrameBuffer[2];
    frameBuffers[0] = new FrameBuffer();
    frameBuffers[1] = new FrameBuffer();
    current = 0;
    next = 1;
  }

  public void draw(List<? extends Pair<Integer, Integer>> coordinateList) {
    LOGGER.info("Start drawing next frame");
    LOGGER.info("Current buffer: " + current + " Next buffer: " + next);
    frameBuffers[next].clearAll();
    coordinateList.forEach(
        coordinate -> {
          var x = coordinate.getKey();
          var y = coordinate.getValue();
          frameBuffers[next].draw(x, y);
        });
    LOGGER.info("Swap current and next buffer");
    swap();
    LOGGER.info("Finish swapping");
    LOGGER.info("Current buffer: " + current + " Next buffer: " + next);
  }

  public Buffer getBuffer() {
    LOGGER.info("Get current buffer: " + current);
    return frameBuffers[current];
  }

  private void swap() {
    current = current ^ next;
    next = current ^ next;
    current = current ^ next;
  }
}
