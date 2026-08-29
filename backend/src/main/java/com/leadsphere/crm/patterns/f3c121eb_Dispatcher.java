package com.leadsphere.crm.patterns;

import com.iluwatar.flux.action.Action;
import com.iluwatar.flux.action.Content;
import com.iluwatar.flux.action.ContentAction;
import com.iluwatar.flux.action.MenuAction;
import com.iluwatar.flux.action.MenuItem;
import com.iluwatar.flux.store.Store;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;

public final class Dispatcher {

  @Getter private static Dispatcher instance = new Dispatcher();

  private final List<Store> stores = new LinkedList<>();

  private Dispatcher() {}

  public void registerStore(Store store) {
    stores.add(store);
  }

  public void menuItemSelected(MenuItem menuItem) {
    dispatchAction(new MenuAction(menuItem));
    if (menuItem == MenuItem.COMPANY) {
      dispatchAction(new ContentAction(Content.COMPANY));
    } else {
      dispatchAction(new ContentAction(Content.PRODUCTS));
    }
  }

  private void dispatchAction(Action action) {
    stores.forEach(store -> store.onAction(action));
  }
}
