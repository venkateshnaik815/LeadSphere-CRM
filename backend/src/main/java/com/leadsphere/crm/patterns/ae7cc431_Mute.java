package com.leadsphere.crm.patterns;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Mute {

  // The constructor is never meant to be called.
  private Mute() {}

  public static void mute(CheckedRunnable runnable) {
    try {
      runnable.run();
    } catch (Exception e) {
      throw new AssertionError(e);
    }
  }

  public static void loggedMute(CheckedRunnable runnable) {
    try {
      runnable.run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
