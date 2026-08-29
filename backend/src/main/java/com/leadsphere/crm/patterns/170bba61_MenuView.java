package com.leadsphere.crm.patterns;

import com.iluwatar.flux.action.MenuItem;
import com.iluwatar.flux.dispatcher.Dispatcher;
import com.iluwatar.flux.store.MenuStore;
import com.iluwatar.flux.store.Store;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MenuView implements View {

  private MenuItem selected = MenuItem.HOME;

  @Override
  public void storeChanged(Store store) {
    var menuStore = (MenuStore) store;
    selected = menuStore.getSelected();
    render();
  }

  @Override
  public void render() {
    for (var item : MenuItem.values()) {
      if (selected.equals(item)) {
        LOGGER.info("* {}", item);
      } else {
        LOGGER.info(item.toString());
      }
    }
  }

  public void itemClicked(MenuItem item) {
    Dispatcher.getInstance().menuItemSelected(item);
  }
}
