package com.leadsphere.crm.patterns;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Album {
  private String title;

  private String artist;

  private boolean isClassical;

  private String composer;
}
