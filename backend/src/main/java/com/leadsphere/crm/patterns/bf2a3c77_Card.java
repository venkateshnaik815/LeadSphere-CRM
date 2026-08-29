package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {

  private long id;

  private long personId;

  private float sum;

  private int version;
}
