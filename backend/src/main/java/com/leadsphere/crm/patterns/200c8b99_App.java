package com.leadsphere.crm.patterns;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  private static final Servant jenkins = new Servant("Jenkins");
  private static final Servant travis = new Servant("Travis");

  public static void main(String[] args) {
    scenario(jenkins, 1);
    scenario(travis, 0);
  }

  public static void scenario(Servant servant, int compliment) {
    var k = new King();
    var q = new Queen();

    var guests = List.of(k, q);

    // feed
    servant.feed(k);
    servant.feed(q);
    // serve drinks
    servant.giveWine(k);
    servant.giveWine(q);
    // compliment
    servant.giveCompliments(guests.get(compliment));

    // outcome of the night
    guests.forEach(Royalty::changeMood);

    // check your luck
    if (servant.checkIfYouWillBeHanged(guests)) {
      LOGGER.info("{} will live another day", servant.name);
    } else {
      LOGGER.info("Poor {}. His days are numbered", servant.name);
    }
  }
}
