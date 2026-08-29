package com.leadsphere.crm.patterns;

import com.iluwatar.flux.action.Content;
import com.iluwatar.flux.store.ContentStore;
import com.iluwatar.flux.store.Store;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContentView implements View {

  private Content content = Content.PRODUCTS;

  @Override
  public void storeChanged(Store store) {
    var contentStore = (ContentStore) store;
    content = contentStore.getContent();
    render();
  }

  @Override
  public void render() {
    LOGGER.info(content.toString());
  }
}
