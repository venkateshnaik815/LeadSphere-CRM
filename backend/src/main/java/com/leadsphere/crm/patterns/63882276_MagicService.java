package com.leadsphere.crm.patterns;

import com.iluwatar.servicelayer.spell.Spell;
import com.iluwatar.servicelayer.spellbook.Spellbook;
import com.iluwatar.servicelayer.wizard.Wizard;
import java.util.List;

public interface MagicService {

  List<Wizard> findAllWizards();

  List<Spellbook> findAllSpellbooks();

  List<Spell> findAllSpells();

  List<Wizard> findWizardsWithSpellbook(String name);

  List<Wizard> findWizardsWithSpell(String name);
}
