package com.leadsphere.crm.patterns;

public interface KingdomFactory {

  Castle createCastle();

  King createKing();

  Army createArmy();
}
