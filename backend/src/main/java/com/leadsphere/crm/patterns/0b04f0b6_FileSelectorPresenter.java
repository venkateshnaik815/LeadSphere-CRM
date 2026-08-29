package com.leadsphere.crm.patterns;

import java.io.Serial;
import java.io.Serializable;

public class FileSelectorPresenter implements Serializable {

  @Serial private static final long serialVersionUID = 1210314339075855074L;

  private final FileSelectorView view;

  private FileLoader loader;

  public FileSelectorPresenter(FileSelectorView view) {
    this.view = view;
  }

  public void setLoader(FileLoader loader) {
    this.loader = loader;
  }

  public void start() {
    view.setPresenter(this);
    view.open();
  }

  public void fileNameChanged() {
    loader.setFileName(view.getFileName());
  }

  public void confirmed() {
    if (loader.getFileName() == null || loader.getFileName().isEmpty()) {
      view.showMessage("Please give the name of the file first!");
      return;
    }

    if (loader.fileExists()) {
      var data = loader.loadData();
      view.displayData(data);
    } else {
      view.showMessage("The file specified does not exist.");
    }
  }

  public void cancelled() {
    view.close();
  }
}
