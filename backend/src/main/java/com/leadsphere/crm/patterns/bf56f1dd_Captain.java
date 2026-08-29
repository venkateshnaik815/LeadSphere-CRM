package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class Captain {

  private RowingBoat rowingBoat;

  void row() {
    rowingBoat.row();
  }
}
