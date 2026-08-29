package com.leadsphere.crm.patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class Validator<T> {
  private final T obj;

  private final List<Throwable> exceptions = new ArrayList<>();

  private Validator(T obj) {
    this.obj = obj;
  }

  public static <T> Validator<T> of(T t) {
    return new Validator<>(Objects.requireNonNull(t));
  }

  public Validator<T> validate(Predicate<? super T> validation, String message) {
    if (!validation.test(obj)) {
      exceptions.add(new IllegalStateException(message));
    }
    return this;
  }

  public <U> Validator<T> validate(
      Function<? super T, ? extends U> projection,
      Predicate<? super U> validation,
      String message) {
    return validate(projection.andThen(validation::test)::apply, message);
  }

  public T get() throws IllegalStateException {
    if (exceptions.isEmpty()) {
      return obj;
    }
    var e = new IllegalStateException();
    exceptions.forEach(e::addSuppressed);
    throw e;
  }
}
