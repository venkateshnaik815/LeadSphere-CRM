package com.leadsphere.crm.patterns;

public final class RoyaltyObjectMother {

  public static King createSoberUnhappyKing() {
    return new King();
  }

  public static King createDrunkKing() {
    var king = new King();
    king.makeDrunk();
    return king;
  }

  public static King createHappyKing() {
    var king = new King();
    king.makeHappy();
    return king;
  }

  public static King createHappyDrunkKing() {
    var king = new King();
    king.makeHappy();
    king.makeDrunk();
    return king;
  }

  public static Queen createFlirtyQueen() {
    var queen = new Queen();
    queen.setFlirtiness(true);
    return queen;
  }

  public static Queen createNotFlirtyQueen() {
    return new Queen();
  }
}
