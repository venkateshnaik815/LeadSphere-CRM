package com.leadsphere.crm.patterns;

import com.iluwatar.property.Character.Type;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    var charProto = new Character();
    charProto.set(Stats.STRENGTH, 10);
    charProto.set(Stats.AGILITY, 10);
    charProto.set(Stats.ARMOR, 10);
    charProto.set(Stats.ATTACK_POWER, 10);

    var mageProto = new Character(Type.MAGE, charProto);
    mageProto.set(Stats.INTELLECT, 15);
    mageProto.set(Stats.SPIRIT, 10);

    var warProto = new Character(Type.WARRIOR, charProto);
    warProto.set(Stats.RAGE, 15);
    warProto.set(Stats.ARMOR, 15); // boost default armor for warrior

    var rogueProto = new Character(Type.ROGUE, charProto);
    rogueProto.set(Stats.ENERGY, 15);
    rogueProto.set(Stats.AGILITY, 15); // boost default agility for rogue

    var mag = new Character("Player_1", mageProto);
    mag.set(Stats.ARMOR, 8);
    LOGGER.info(mag.toString());

    var warrior = new Character("Player_2", warProto);
    LOGGER.info(warrior.toString());

    var rogue = new Character("Player_3", rogueProto);
    LOGGER.info(rogue.toString());

    var rogueDouble = new Character("Player_4", rogue);
    rogueDouble.set(Stats.ATTACK_POWER, 12);
    LOGGER.info(rogueDouble.toString());
  }
}
