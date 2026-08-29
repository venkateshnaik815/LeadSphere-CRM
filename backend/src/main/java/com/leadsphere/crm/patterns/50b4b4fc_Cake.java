
package com.leadsphere.crm.patterns;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cake {

  @Id @GeneratedValue private Long id;

  @OneToOne(cascade = CascadeType.REMOVE)
  private CakeTopping topping;

  @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
  private Set<CakeLayer> layers;

  public Cake() {
    setLayers(new HashSet<>());
  }

  public void addLayer(CakeLayer layer) {
    this.layers.add(layer);
  }

  @Override
  public String toString() {
    return String.format("id=%s topping=%s layers=%s", id, topping, layers.toString());
  }
}
