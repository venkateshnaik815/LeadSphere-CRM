package com.leadsphere.crm.patterns;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public abstract class Task {

  final void executeWith(Callback callback) {
    execute();
    Optional.ofNullable(callback).ifPresent(Callback::call);
  }

  final CompletableFuture<Void> executeAsyncWith(Callback callback) {
    return CompletableFuture.runAsync(
        () -> {
          execute();
          Optional.ofNullable(callback).ifPresent(Callback::call);
        });
  }

  public abstract void execute();
}
