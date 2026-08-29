package com.leadsphere.crm.patterns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class Action {

  private final ActionType type;
}
