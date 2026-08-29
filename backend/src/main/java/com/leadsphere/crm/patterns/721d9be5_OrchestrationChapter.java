package com.leadsphere.crm.patterns;

public interface OrchestrationChapter<K> {

  String getName();

  ChapterResult<K> process(K value);

  ChapterResult<K> rollback(K value);
}
