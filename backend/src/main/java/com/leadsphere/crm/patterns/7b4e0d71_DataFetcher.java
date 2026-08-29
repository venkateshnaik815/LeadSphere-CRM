package com.leadsphere.crm.patterns;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataFetcher {

  private static final String FILENAME = "world.txt";
  private long lastFetched;

  public DataFetcher() {
    this.lastFetched = -1;
  }

  private boolean isDirty(long fileLastModified) {
    if (lastFetched != fileLastModified) {
      lastFetched = fileLastModified;
      return true;
    }
    return false;
  }

  public List<String> fetch() {
    var classLoader = getClass().getClassLoader();
    var file = new File(classLoader.getResource(FILENAME).getFile());

    if (isDirty(file.lastModified())) {
      LOGGER.info(FILENAME + " is dirty! Re-fetching file content...");
      try (var br = new BufferedReader(new FileReader(file))) {
        return br.lines().collect(Collectors.collectingAndThen(Collectors.toList(), List::copyOf));
      } catch (IOException e) {
        LOGGER.error("An error occurred: ", e);
      }
    }

    return List.of();
  }
}
