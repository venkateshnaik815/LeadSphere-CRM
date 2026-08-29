package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    var givenTime = 50; // 50ms
    var toWin = 500; // points
    var pointsWon = 0;
    var numOfRows = 3;
    var start = System.currentTimeMillis();
    var end = System.currentTimeMillis();
    var round = 0;
    while (pointsWon < toWin && end - start < givenTime) {
      round++;
      var pool = new CellPool(numOfRows * numOfRows + 5);
      var cg = new CandyGame(numOfRows, pool);
      if (round > 1) {
        LOGGER.info("Refreshing..");
      } else {
        LOGGER.info("Starting game..");
      }
      cg.printGameStatus();
      end = System.currentTimeMillis();
      cg.round((int) (end - start), givenTime);
      pointsWon += cg.totalPoints;
      end = System.currentTimeMillis();
    }
    LOGGER.info("Game Over");
    if (pointsWon >= toWin) {
      LOGGER.info("" + pointsWon);
      LOGGER.info("You win!!");
    } else {
      LOGGER.info("" + pointsWon);
      LOGGER.info("Sorry, you lose!");
    }
  }
}
