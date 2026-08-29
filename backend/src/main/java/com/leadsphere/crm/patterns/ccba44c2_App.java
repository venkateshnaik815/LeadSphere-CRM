package com.leadsphere.crm.patterns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.sound.sampled.UnsupportedAudioFileException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args)
      throws UnsupportedAudioFileException, IOException, InterruptedException {
    var audio = Audio.getInstance();
    audio.playSound(audio.getAudioStream("./etc/Bass-Drum-1.wav"), -10.0f);
    audio.playSound(audio.getAudioStream("./etc/Closed-Hi-Hat-1.wav"), -8.0f);

    LOGGER.info("Press Enter key to stop the program...");
    try (var br = new BufferedReader(new InputStreamReader(System.in))) {
      br.read();
    }
    audio.stopService();
  }
}
