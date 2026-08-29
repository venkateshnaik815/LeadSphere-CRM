package com.leadsphere.crm.patterns;

import com.iluwatar.servicelayer.common.BaseEntity;
import com.iluwatar.servicelayer.spell.Spell;
import com.iluwatar.servicelayer.wizard.Wizard;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "SPELLBOOK")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Spellbook extends BaseEntity {

  @Id
  @GeneratedValue
  @Column(name = "SPELLBOOK_ID")
  private Long id;

  private String name;

  @ManyToMany(mappedBy = "spellbooks", fetch = FetchType.EAGER)
  private Set<Wizard> wizards;

  @OneToMany(mappedBy = "spellbook", orphanRemoval = true, cascade = CascadeType.ALL)
  private Set<Spell> spells;

  public Spellbook() {
    spells = new HashSet<>();
    wizards = new HashSet<>();
  }

  public Spellbook(String name) {
    this();
    this.name = name;
  }

  public void addSpell(Spell spell) {
    spell.setSpellbook(this);
    spells.add(spell);
  }

  @Override
  public String toString() {
    return name;
  }
}
