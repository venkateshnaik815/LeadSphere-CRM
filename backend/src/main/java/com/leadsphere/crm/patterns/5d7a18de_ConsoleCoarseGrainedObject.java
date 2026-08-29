package com.leadsphere.crm.patterns;

public class ConsoleCoarseGrainedObject extends CoarseGrainedObject<String> {

  @Override
  public String[] getData() {
    return new String[] {dependentObjects[0].getData(), dependentObjects[1].getData()};
  }

  public void init() {
    dependentObjects =
        new DependentObject[] {new MessageDependentObject(), new SignalDependentObject()};
  }
}
