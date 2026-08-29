package com.leadsphere.crm.patterns;

public interface UnitVisitor {

  void visit(Soldier soldier);

  void visit(Sergeant sergeant);

  void visit(Commander commander);
}
