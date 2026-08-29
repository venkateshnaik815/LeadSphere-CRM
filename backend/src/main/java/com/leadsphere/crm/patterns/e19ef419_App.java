package com.leadsphere.crm.patterns;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) throws IOException {

    // create the file writer and execute the custom action
    FileWriterAction writeHello = writer -> writer.write("Gandalf was here");
    new SimpleFileWriter("testfile.txt", writeHello);

    // print the file contents
    try (var scanner = new Scanner(new File("testfile.txt"))) {
      while (scanner.hasNextLine()) {
        LOGGER.info(scanner.nextLine());
      }
    }
  }
}
