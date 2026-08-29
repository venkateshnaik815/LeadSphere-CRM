package com.leadsphere.crm.patterns;

import com.iluwatar.flux.action.Action;
import com.iluwatar.flux.action.ActionType;
import com.iluwatar.flux.action.MenuAction;
import com.iluwatar.flux.action.MenuItem;
import lombok.Getter;

public class MenuStore extends Store {

  @Getter private MenuItem selected = MenuItem.HOME;

  @Override
  public void onAction(Action action) {
    if (action.getType().equals(ActionType.MENU_ITEM_SELECTED)) {
      var menuAction = (MenuAction) action;
      selected = menuAction.getMenuItem();
      notifyChange();
    }
  }
}
