package com.leadsphere.crm.patterns;

import lombok.Getter;

public class ContentAction extends Action {

  @Getter private final Content content;

  public ContentAction(Content content) {
    super(ActionType.CONTENT_CHANGED);
    this.content = content;
  }
}
