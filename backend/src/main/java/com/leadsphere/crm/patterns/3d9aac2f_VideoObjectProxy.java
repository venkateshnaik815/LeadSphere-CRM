
package com.leadsphere.crm.patterns;

import lombok.Getter;

@Getter
public class VideoObjectProxy implements ExpensiveObject {
  private RealVideoObject realVideoObject;

  @Override
  public void process() {
    if (realVideoObject == null) {
      realVideoObject = new RealVideoObject();
    }
    realVideoObject.process();
  }
}
