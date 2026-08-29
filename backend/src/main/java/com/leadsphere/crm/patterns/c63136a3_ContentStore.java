package com.leadsphere.crm.patterns;

import com.iluwatar.flux.action.Action;
import com.iluwatar.flux.action.ActionType;
import com.iluwatar.flux.action.Content;
import com.iluwatar.flux.action.ContentAction;
import lombok.Getter;

public class ContentStore extends Store {

  @Getter private Content content = Content.PRODUCTS;

  @Override
  public void onAction(Action action) {
    if (action.getType().equals(ActionType.CONTENT_CHANGED)) {
      var contentAction = (ContentAction) action;
      content = contentAction.getContent();
      notifyChange();
    }
  }
}
