package com.leadsphere.crm.patterns;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public final class App {

  private App() {}

  public static void main(String[] args) {

    try {
      var classLoader = App.class.getClassLoader();
      var applicationFile = new File(classLoader.getResource("sample-ui/login.html").getPath());

      // Should work for unix like OS (mac, unix etc...)
      if (Desktop.isDesktopSupported()) {
        Desktop.getDesktop().open(applicationFile);

      } else {
        // java Desktop not supported - use ProcessBuilder for cross-platform support
        var os = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        ProcessBuilder pb;
        if (os.contains("win")) {
          // Empty string title arg prevents cmd start treating a quoted path as the window title
          var systemRoot = System.getenv("SystemRoot");
          if (systemRoot == null) {
            systemRoot = "C:\\Windows";
          }
          pb =
              new ProcessBuilder(
                  systemRoot + "\\System32\\cmd.exe",
                  "/c",
                  "start",
                  "",
                  applicationFile.getAbsolutePath());
        } else if (os.contains("mac")) {
          pb = new ProcessBuilder("open", applicationFile.getAbsolutePath()); // NOSONAR
        } else {
          pb = new ProcessBuilder("xdg-open", applicationFile.getAbsolutePath()); // NOSONAR
        }
        pb.start();
      }

    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
