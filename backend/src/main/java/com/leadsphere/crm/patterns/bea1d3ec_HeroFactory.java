package com.leadsphere.crm.patterns;

public interface HeroFactory {

  Mage createMage();

  Warlord createWarlord();

  Beast createBeast();
}
