package com.leadsphere.crm.patterns;

import com.iluwatar.servicelayer.magic.MagicService;
import com.iluwatar.servicelayer.magic.MagicServiceImpl;
import com.iluwatar.servicelayer.spell.Spell;
import com.iluwatar.servicelayer.spell.SpellDaoImpl;
import com.iluwatar.servicelayer.spellbook.Spellbook;
import com.iluwatar.servicelayer.spellbook.SpellbookDaoImpl;
import com.iluwatar.servicelayer.wizard.Wizard;
import com.iluwatar.servicelayer.wizard.WizardDaoImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
  public static final String BOOK_OF_IDORES = "Book of Idores";

  public static void main(String[] args) {
    // populate the in-memory database
    initData();
    // query the data using the service
    queryData();
  }

  public static void initData() {
    // spells
    var spell1 = new Spell("Ice dart");
    var spell2 = new Spell("Invisibility");
    var spell3 = new Spell("Stun bolt");
    var spell4 = new Spell("Confusion");
    var spell5 = new Spell("Darkness");
    var spell6 = new Spell("Fireball");
    var spell7 = new Spell("Enchant weapon");
    var spell8 = new Spell("Rock armour");
    var spell9 = new Spell("Light");
    var spell10 = new Spell("Bee swarm");
    var spell11 = new Spell("Haste");
    var spell12 = new Spell("Levitation");
    var spell13 = new Spell("Magic lock");
    var spell14 = new Spell("Summon hell bat");
    var spell15 = new Spell("Water walking");
    var spell16 = new Spell("Magic storm");
    var spell17 = new Spell("Entangle");
    var spellDao = new SpellDaoImpl();
    spellDao.persist(spell1);
    spellDao.persist(spell2);
    spellDao.persist(spell3);
    spellDao.persist(spell4);
    spellDao.persist(spell5);
    spellDao.persist(spell6);
    spellDao.persist(spell7);
    spellDao.persist(spell8);
    spellDao.persist(spell9);
    spellDao.persist(spell10);
    spellDao.persist(spell11);
    spellDao.persist(spell12);
    spellDao.persist(spell13);
    spellDao.persist(spell14);
    spellDao.persist(spell15);
    spellDao.persist(spell16);
    spellDao.persist(spell17);

    // spellbooks
    var spellbookDao = new SpellbookDaoImpl();
    var spellbook1 = new Spellbook("Book of Orgymon");
    spellbookDao.persist(spellbook1);
    spellbook1.addSpell(spell1);
    spellbook1.addSpell(spell2);
    spellbook1.addSpell(spell3);
    spellbook1.addSpell(spell4);
    spellbookDao.merge(spellbook1);
    var spellbook2 = new Spellbook("Book of Aras");
    spellbookDao.persist(spellbook2);
    spellbook2.addSpell(spell5);
    spellbook2.addSpell(spell6);
    spellbookDao.merge(spellbook2);
    var spellbook3 = new Spellbook("Book of Kritior");
    spellbookDao.persist(spellbook3);
    spellbook3.addSpell(spell7);
    spellbook3.addSpell(spell8);
    spellbook3.addSpell(spell9);
    spellbookDao.merge(spellbook3);
    var spellbook4 = new Spellbook("Book of Tamaex");
    spellbookDao.persist(spellbook4);
    spellbook4.addSpell(spell10);
    spellbook4.addSpell(spell11);
    spellbook4.addSpell(spell12);
    spellbookDao.merge(spellbook4);
    var spellbook5 = new Spellbook(BOOK_OF_IDORES);
    spellbookDao.persist(spellbook5);
    spellbook5.addSpell(spell13);
    spellbookDao.merge(spellbook5);
    var spellbook6 = new Spellbook("Book of Opaen");
    spellbookDao.persist(spellbook6);
    spellbook6.addSpell(spell14);
    spellbook6.addSpell(spell15);
    spellbookDao.merge(spellbook6);
    var spellbook7 = new Spellbook("Book of Kihione");
    spellbookDao.persist(spellbook7);
    spellbook7.addSpell(spell16);
    spellbook7.addSpell(spell17);
    spellbookDao.merge(spellbook7);

    // wizards
    var wizardDao = new WizardDaoImpl();
    var wizard1 = new Wizard("Aderlard Boud");
    wizardDao.persist(wizard1);
    wizard1.addSpellbook(spellbookDao.findByName("Book of Orgymon"));
    wizard1.addSpellbook(spellbookDao.findByName("Book of Aras"));
    wizardDao.merge(wizard1);
    var wizard2 = new Wizard("Anaxis Bajraktari");
    wizardDao.persist(wizard2);
    wizard2.addSpellbook(spellbookDao.findByName("Book of Kritior"));
    wizard2.addSpellbook(spellbookDao.findByName("Book of Tamaex"));
    wizardDao.merge(wizard2);
    var wizard3 = new Wizard("Xuban Munoa");
    wizardDao.persist(wizard3);
    wizard3.addSpellbook(spellbookDao.findByName(BOOK_OF_IDORES));
    wizard3.addSpellbook(spellbookDao.findByName("Book of Opaen"));
    wizardDao.merge(wizard3);
    var wizard4 = new Wizard("Blasius Dehooge");
    wizardDao.persist(wizard4);
    wizard4.addSpellbook(spellbookDao.findByName("Book of Kihione"));
    wizardDao.merge(wizard4);
  }

  public static void queryData() {
    var wizardDao = new WizardDaoImpl();
    var spellbookDao = new SpellbookDaoImpl();
    var spellDao = new SpellDaoImpl();
    var service = new MagicServiceImpl(wizardDao, spellbookDao, spellDao);
    LOGGER.info("Enumerating all wizards");
    service.findAllWizards().stream().map(Wizard::getName).forEach(LOGGER::info);
    LOGGER.info("Enumerating all spellbooks");
    service.findAllSpellbooks().stream().map(Spellbook::getName).forEach(LOGGER::info);
    LOGGER.info("Enumerating all spells");
    service.findAllSpells().stream().map(Spell::getName).forEach(LOGGER::info);
    LOGGER.info("Find wizards with spellbook 'Book of Idores'");
    var wizardsWithSpellbook = service.findWizardsWithSpellbook(BOOK_OF_IDORES);
    wizardsWithSpellbook.forEach(w -> LOGGER.info("{} has 'Book of Idores'", w.getName()));
    LOGGER.info("Find wizards with spell 'Fireball'");
    var wizardsWithSpell = service.findWizardsWithSpell("Fireball");
    wizardsWithSpell.forEach(w -> LOGGER.info("{} has 'Fireball'", w.getName()));
  }
}
