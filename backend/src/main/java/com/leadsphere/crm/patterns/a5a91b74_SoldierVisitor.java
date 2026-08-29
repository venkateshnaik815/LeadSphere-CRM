package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SoldierVisitor implements UnitVisitor {

  @Override
  public void visit(Soldier soldier) {
    LOGGER.info("Greetings {}", soldier);
  }

  @Override
  public void visit(Sergeant sergeant) {
    // Do nothing
  }

  @Override
  public void visit(Commander commander) {
    // Do nothing
  }
}
