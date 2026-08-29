package com.leadsphere.crm.patterns;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class DependentObject<T> {

  T data;
}
