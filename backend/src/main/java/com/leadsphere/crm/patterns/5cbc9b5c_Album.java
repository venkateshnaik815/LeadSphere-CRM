package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class Album {

  private Integer id;
  private String title;
  private Integer userId;
}
