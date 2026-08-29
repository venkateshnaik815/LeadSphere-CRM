package com.leadsphere.crm.patterns;

import java.util.concurrent.Callable;

public interface AsyncTask<O> extends Callable<O> {
  void onPreCall();

  void onPostCall(O result);

  void onError(Throwable throwable);

  @Override
  O call() throws Exception;
}
