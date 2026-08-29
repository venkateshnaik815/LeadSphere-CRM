package com.leadsphere.crm.patterns;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.MutablePair;

@Slf4j
public class App {

  public static void main(String[] args) {
    final var scene = new Scene();
    var drawPixels1 =
        List.of(new MutablePair<>(1, 1), new MutablePair<>(5, 6), new MutablePair<>(3, 2));
    scene.draw(drawPixels1);
    var buffer1 = scene.getBuffer();
    printBlackPixelCoordinate(buffer1);

    var drawPixels2 = List.of(new MutablePair<>(3, 7), new MutablePair<>(6, 1));
    scene.draw(drawPixels2);
    var buffer2 = scene.getBuffer();
    printBlackPixelCoordinate(buffer2);
  }

  private static void printBlackPixelCoordinate(Buffer buffer) {
    StringBuilder log = new StringBuilder("Black Pixels: ");
    var pixels = buffer.getPixels();
    for (var i = 0; i < pixels.length; ++i) {
      if (pixels[i] == Pixel.BLACK) {
        var y = i / FrameBuffer.WIDTH;
        var x = i % FrameBuffer.WIDTH;
        log.append(" (").append(x).append(", ").append(y).append(")");
      }
    }
    LOGGER.info(log.toString());
  }
}
