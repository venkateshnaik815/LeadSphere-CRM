package com.leadsphere.crm.patterns;

import com.iluwatar.flux.store.Store;

public interface View {

  void storeChanged(Store store);

  void render();
}
