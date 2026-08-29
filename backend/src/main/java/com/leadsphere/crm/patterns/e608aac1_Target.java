package com.leadsphere.crm.patterns;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public abstract class Target {

  private Size size;

  private Visibility visibility;

  public void printStatus() {
    LOGGER.info("{}, [size={}] [visibility={}]", this, getSize(), getVisibility());
  }

  public void changeSize() {
    var oldSize = getSize() == Size.NORMAL ? Size.SMALL : Size.NORMAL;
    setSize(oldSize);
  }

  public void changeVisibility() {
    var visible =
        getVisibility() == Visibility.INVISIBLE ? Visibility.VISIBLE : Visibility.INVISIBLE;
    setVisibility(visible);
  }
}
