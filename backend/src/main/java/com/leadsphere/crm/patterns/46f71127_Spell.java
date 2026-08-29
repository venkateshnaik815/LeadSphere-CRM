package com.leadsphere.crm.patterns;

import com.iluwatar.servicelayer.common.BaseEntity;
import com.iluwatar.servicelayer.spellbook.Spellbook;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "SPELL")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Spell extends BaseEntity {

  private String name;

  @Id
  @GeneratedValue
  @Column(name = "SPELL_ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "SPELLBOOK_ID_FK", referencedColumnName = "SPELLBOOK_ID")
  private Spellbook spellbook;

  public Spell() {}

  public Spell(String name) {
    this();
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
