package com.leadsphere.crm.patterns;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    // Write V1
    var fishV1 = new RainbowFish("Zed", 10, 11, 12);
    LOGGER.info(
        "fishV1 name={} age={} length={} weight={}",
        fishV1.getName(),
        fishV1.getAge(),
        fishV1.getLengthMeters(),
        fishV1.getWeightTons());
    RainbowFishSerializer.writeV1(fishV1, "fish1.out");
    // Read V1
    var deserializedRainbowFishV1 = RainbowFishSerializer.readV1("fish1.out");
    LOGGER.info(
        "deserializedFishV1 name={} age={} length={} weight={}",
        deserializedRainbowFishV1.getName(),
        deserializedRainbowFishV1.getAge(),
        deserializedRainbowFishV1.getLengthMeters(),
        deserializedRainbowFishV1.getWeightTons());
    // Write V2
    var fishV2 = new RainbowFishV2("Scar", 5, 12, 15, true, true, true);
    LOGGER.info(
        "fishV2 name={} age={} length={} weight={} sleeping={} hungry={} angry={}",
        fishV2.getName(),
        fishV2.getAge(),
        fishV2.getLengthMeters(),
        fishV2.getWeightTons(),
        fishV2.isHungry(),
        fishV2.isAngry(),
        fishV2.isSleeping());
    RainbowFishSerializer.writeV2(fishV2, "fish2.out");
    // Read V2 with V1 method
    var deserializedFishV2 = RainbowFishSerializer.readV1("fish2.out");
    LOGGER.info(
        "deserializedFishV2 name={} age={} length={} weight={}",
        deserializedFishV2.getName(),
        deserializedFishV2.getAge(),
        deserializedFishV2.getLengthMeters(),
        deserializedFishV2.getWeightTons());
  }
}
