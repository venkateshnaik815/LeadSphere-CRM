
package com.leadsphere.crm.patterns;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class CakeTopping {

  @Id @GeneratedValue private Long id;

  private String name;

  private int calories;

  @OneToOne(cascade = CascadeType.ALL)
  private Cake cake;

  public CakeTopping(String name, int calories) {
    this.setName(name);
    this.setCalories(calories);
  }

  @Override
  public String toString() {
    return String.format("id=%s name=%s calories=%d", id, name, calories);
  }
}
