package com.leadsphere.crm.patterns;

import lombok.Data;
import lombok.Getter;

@Data
public class CalculatorModel {

  @Getter private final Double variable;

  @Getter private final Double output;
}
