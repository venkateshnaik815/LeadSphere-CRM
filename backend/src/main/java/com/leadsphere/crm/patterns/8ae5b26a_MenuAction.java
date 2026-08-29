package com.leadsphere.crm.patterns;

import lombok.Getter;

public class MenuAction extends Action {

  @Getter private final MenuItem menuItem;

  public MenuAction(MenuItem menuItem) {
    super(ActionType.MENU_ITEM_SELECTED);
    this.menuItem = menuItem;
  }
}
