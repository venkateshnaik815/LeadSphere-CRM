package com.leadsphere.crm.patterns;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Audio {
  private static final Audio INSTANCE = new Audio();

  private static final int MAX_PENDING = 16;

  private int headIndex;

  private int tailIndex;

  private volatile Thread updateThread = null;

  @Getter private final PlayMessage[] pendingAudio = new PlayMessage[MAX_PENDING];

  // Visible only for testing purposes
  Audio() {}

  public static Audio getInstance() {
    return INSTANCE;
  }

  public synchronized void stopService() throws InterruptedException {
    if (updateThread != null) {
      updateThread.interrupt();
    }
    updateThread.join();
    updateThread = null;
  }

  public synchronized boolean isServiceRunning() {
    return updateThread != null && updateThread.isAlive();
  }

  public void init() {
    if (updateThread == null) {
      updateThread =
          new Thread(
              () -> {
                while (!Thread.currentThread().isInterrupted()) {
                  update();
                }
              });
    }
    startThread();
  }

  private synchronized void startThread() {
    if (!updateThread.isAlive()) {
      updateThread.start();
      headIndex = 0;
      tailIndex = 0;
    }
  }

  public void playSound(AudioInputStream stream, float volume) {
    init();
    // Walk the pending requests.
    for (var i = headIndex; i != tailIndex; i = (i + 1) % MAX_PENDING) {
      var playMessage = getPendingAudio()[i];
      if (playMessage.getStream() == stream) {
        // Use the larger of the two volumes.
        playMessage.setVolume(Math.max(volume, playMessage.getVolume()));

        // Don't need to enqueue.
        return;
      }
    }
    getPendingAudio()[tailIndex] = new PlayMessage(stream, volume);
    tailIndex = (tailIndex + 1) % MAX_PENDING;
  }

  private void update() {
    // If there are no pending requests, do nothing.
    if (headIndex == tailIndex) {
      return;
    }
    try {
      var audioStream = getPendingAudio()[headIndex].getStream();
      headIndex++;
      var clip = AudioSystem.getClip();
      clip.open(audioStream);
      clip.start();
    } catch (LineUnavailableException e) {
      LOGGER.trace("Error occurred while loading the audio: The line is unavailable", e);
    } catch (IOException e) {
      LOGGER.trace("Input/Output error while loading the audio", e);
    } catch (IllegalArgumentException e) {
      LOGGER.trace("The system doesn't support the sound: " + e.getMessage(), e);
    }
  }

  public AudioInputStream getAudioStream(String filePath)
      throws UnsupportedAudioFileException, IOException {
    return AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
  }
}
