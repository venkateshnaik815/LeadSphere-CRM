package com.leadsphere.crm.patterns;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class RainbowFishSerializer {

  public static final String LENGTH_METERS = "lengthMeters";
  public static final String WEIGHT_TONS = "weightTons";

  public static void writeV1(RainbowFish rainbowFish, String filename) throws IOException {
    var map =
        Map.of(
            "name",
            rainbowFish.getName(),
            "age",
            String.format("%d", rainbowFish.getAge()),
            LENGTH_METERS,
            String.format("%d", rainbowFish.getLengthMeters()),
            WEIGHT_TONS,
            String.format("%d", rainbowFish.getWeightTons()));

    try (var fileOut = new FileOutputStream(filename);
        var objOut = new ObjectOutputStream(fileOut)) {
      objOut.writeObject(map);
    }
  }

  public static void writeV2(RainbowFishV2 rainbowFish, String filename) throws IOException {
    var map =
        Map.of(
            "name",
            rainbowFish.getName(),
            "age",
            String.format("%d", rainbowFish.getAge()),
            LENGTH_METERS,
            String.format("%d", rainbowFish.getLengthMeters()),
            WEIGHT_TONS,
            String.format("%d", rainbowFish.getWeightTons()),
            "angry",
            Boolean.toString(rainbowFish.isAngry()),
            "hungry",
            Boolean.toString(rainbowFish.isHungry()),
            "sleeping",
            Boolean.toString(rainbowFish.isSleeping()));

    try (var fileOut = new FileOutputStream(filename);
        var objOut = new ObjectOutputStream(fileOut)) {
      objOut.writeObject(map);
    }
  }

  public static RainbowFish readV1(String filename) throws IOException, ClassNotFoundException {
    Map<String, String> map;

    try (var fileIn = new FileInputStream(filename);
        var objIn = new ObjectInputStream(fileIn)) {
      map = (Map<String, String>) objIn.readObject();
    }

    return new RainbowFish(
        map.get("name"),
        Integer.parseInt(map.get("age")),
        Integer.parseInt(map.get(LENGTH_METERS)),
        Integer.parseInt(map.get(WEIGHT_TONS)));
  }
}
