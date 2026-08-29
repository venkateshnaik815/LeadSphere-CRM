package com.leadsphere.crm.patterns;

import javax.sound.sampled.AudioInputStream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class PlayMessage {

  private final AudioInputStream stream;

  @Setter private float volume;
}
