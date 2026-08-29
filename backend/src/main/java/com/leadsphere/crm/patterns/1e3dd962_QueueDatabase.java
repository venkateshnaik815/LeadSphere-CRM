package com.leadsphere.crm.patterns;

import com.iluwatar.commander.Database;
import com.iluwatar.commander.exceptions.IsEmptyException;
import java.util.ArrayList;
import java.util.List;

public class QueueDatabase extends Database<QueueTask> {

  private final Queue<QueueTask> data;
  public List<Exception> exceptionsList;

  public QueueDatabase(Exception... exc) {
    this.data = new Queue<>();
    this.exceptionsList = new ArrayList<>(List.of(exc));
  }

  @Override
  public QueueTask add(QueueTask t) {
    data.enqueue(t);
    return t;
    // even if same thing queued twice, it is taken care of in other dbs
  }

  public QueueTask peek() throws IsEmptyException {
    return this.data.peek();
  }

  public QueueTask dequeue() throws IsEmptyException {
    return this.data.dequeue();
  }

  @Override
  public QueueTask get(String taskId) {
    return null;
  }
}
