package com.leadsphere.crm.patterns;

public interface ChoreographyChapter {

  Saga execute(Saga saga);

  String getName();

  Saga process(Saga saga);

  Saga rollback(Saga saga);
}
