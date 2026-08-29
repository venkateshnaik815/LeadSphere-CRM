package com.leadsphere.crm.patterns;

import java.io.FileWriter;
import java.io.IOException;

@FunctionalInterface
public interface FileWriterAction {

  void writeFile(FileWriter writer) throws IOException;
}
