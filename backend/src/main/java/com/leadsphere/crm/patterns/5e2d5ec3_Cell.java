package com.leadsphere.crm.patterns;

import com.iluwatar.typeobject.Candy.Type;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Cell {
  Candy candy;
  int positionX;
  int positionY;

  void crush(CellPool pool, Cell[][] cellMatrix) {
    // take out from this position and put back in pool
    pool.addNewCell(this);
    this.fillThisSpace(pool, cellMatrix);
  }

  void fillThisSpace(CellPool pool, Cell[][] cellMatrix) {
    for (var y = this.positionY; y > 0; y--) {
      cellMatrix[y][this.positionX] = cellMatrix[y - 1][this.positionX];
      cellMatrix[y][this.positionX].positionY = y;
    }
    var newC = pool.getNewCell();
    cellMatrix[0][this.positionX] = newC;
    cellMatrix[0][this.positionX].positionX = this.positionX;
    cellMatrix[0][this.positionX].positionY = 0;
  }

  void handleCrush(Cell c, CellPool pool, Cell[][] cellMatrix) {
    if (this.positionY >= c.positionY) {
      this.crush(pool, cellMatrix);
      c.crush(pool, cellMatrix);
    } else {
      c.crush(pool, cellMatrix);
      this.crush(pool, cellMatrix);
    }
  }

  int interact(Cell c, CellPool pool, Cell[][] cellMatrix) {
    if (this.candy.getType().equals(Type.REWARD_FRUIT)
        || c.candy.getType().equals(Type.REWARD_FRUIT)) {
      return 0;
    } else {
      if (this.candy.name.equals(c.candy.name)) {
        var pointsWon = this.candy.getPoints() + c.candy.getPoints();
        handleCrush(c, pool, cellMatrix);
        return pointsWon;
      } else {
        return 0;
      }
    }
  }
}
