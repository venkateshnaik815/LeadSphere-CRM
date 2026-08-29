package com.leadsphere.crm.patterns;

import com.iluwatar.servicelayer.common.BaseEntity;
import com.iluwatar.servicelayer.spellbook.Spellbook;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "WIZARD")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Wizard extends BaseEntity {

  @Id
  @GeneratedValue
  @Column(name = "WIZARD_ID")
  private Long id;

  private String name;

  @ManyToMany(cascade = CascadeType.ALL)
  private Set<Spellbook> spellbooks;

  public Wizard() {
    spellbooks = new HashSet<>();
  }

  public Wizard(String name) {
    this();
    this.name = name;
  }

  public void addSpellbook(Spellbook spellbook) {
    spellbook.getWizards().add(this);
    spellbooks.add(spellbook);
  }

  @Override
  public String toString() {
    return name;
  }
}
