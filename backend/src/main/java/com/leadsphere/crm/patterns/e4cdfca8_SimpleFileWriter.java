package com.leadsphere.crm.patterns;

import java.io.FileWriter;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleFileWriter {

  public SimpleFileWriter(String filename, FileWriterAction action) throws IOException {
    LOGGER.info("Opening the file");
    try (var writer = new FileWriter(filename)) {
      LOGGER.info("Executing the action");
      action.writeFile(writer);
      LOGGER.info("Closing the file");
    }
  }
}
