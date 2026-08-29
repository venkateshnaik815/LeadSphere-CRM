
package com.leadsphere.crm.patterns;

public class App {
  public static void main(String[] args) {
    ExpensiveObject videoObject = new VideoObjectProxy();
    videoObject.process(); // The first call creates and plays the video
    videoObject.process(); // Subsequent call uses the already created object
  }
}
