package com.leadsphere.crm.patterns;

public interface Party {

  void addMember(PartyMember member);

  void act(PartyMember actor, Action action);
}
