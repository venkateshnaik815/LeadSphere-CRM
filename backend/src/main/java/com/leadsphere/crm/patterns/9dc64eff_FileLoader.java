package com.leadsphere.crm.patterns;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serial;
import java.io.Serializable;
import java.util.stream.Collectors;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class FileLoader implements Serializable {

  @Serial private static final long serialVersionUID = -4745803872902019069L;

  private static final Logger LOGGER = LoggerFactory.getLogger(FileLoader.class);

  private boolean loaded;

  private String fileName;

  public String loadData() {
    var dataFileName = this.fileName;
    try (var br = new BufferedReader(new FileReader(dataFileName))) {
      var result = br.lines().collect(Collectors.joining("\n"));
      this.loaded = true;
      return result;
    } catch (Exception e) {
      LOGGER.error("File {} does not exist", dataFileName);
    }

    return null;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public boolean fileExists() {
    return new File(this.fileName).exists();
  }
}
