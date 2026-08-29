package com.leadsphere.crm.patterns;

public class App {

  public static void main(String[] args) {
    var loader = new FileLoader();
    var frame = new FileSelectorJframe();
    var presenter = new FileSelectorPresenter(frame);
    presenter.setLoader(loader);
    presenter.start();
  }
}
