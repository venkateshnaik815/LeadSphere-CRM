package com.leadsphere.crm.patterns;

import com.iluwatar.fluentinterface.fluentiterable.FluentIterable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimpleFluentIterable<E> implements FluentIterable<E> {

  private final Iterable<E> iterable;

  @Override
  public final FluentIterable<E> filter(Predicate<? super E> predicate) {
    var iterator = iterator();
    while (iterator.hasNext()) {
      var nextElement = iterator.next();
      if (!predicate.test(nextElement)) {
        iterator.remove();
      }
    }
    return this;
  }

  @Override
  public final Optional<E> first() {
    var resultIterator = first(1).iterator();
    return resultIterator.hasNext() ? Optional.of(resultIterator.next()) : Optional.empty();
  }

  @Override
  public final FluentIterable<E> first(int count) {
    var iterator = iterator();
    var currentCount = 0;
    while (iterator.hasNext()) {
      iterator.next();
      if (currentCount >= count) {
        iterator.remove();
      }
      currentCount++;
    }
    return this;
  }

  @Override
  public final Optional<E> last() {
    var list = last(1).asList();
    if (list.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(list.get(0));
  }

  @Override
  public final FluentIterable<E> last(int count) {
    var remainingElementsCount = getRemainingElementsCount();
    var iterator = iterator();
    var currentIndex = 0;
    while (iterator.hasNext()) {
      iterator.next();
      if (currentIndex < remainingElementsCount - count) {
        iterator.remove();
      }
      currentIndex++;
    }

    return this;
  }

  @Override
  public final <T> FluentIterable<T> map(Function<? super E, T> function) {
    var temporaryList = new ArrayList<T>();
    this.forEach(e -> temporaryList.add(function.apply(e)));
    return from(temporaryList);
  }

  @Override
  public List<E> asList() {
    return toList(iterable.iterator());
  }

  public static <E> FluentIterable<E> from(Iterable<E> iterable) {
    return new SimpleFluentIterable<>(iterable);
  }

  public static <E> FluentIterable<E> fromCopyOf(Iterable<E> iterable) {
    var copy = FluentIterable.copyToList(iterable);
    return new SimpleFluentIterable<>(copy);
  }

  @Override
  public Iterator<E> iterator() {
    return iterable.iterator();
  }

  @Override
  public void forEach(Consumer<? super E> action) {
    iterable.forEach(action);
  }

  @Override
  public Spliterator<E> spliterator() {
    return iterable.spliterator();
  }

  public final int getRemainingElementsCount() {
    var counter = 0;
    for (var ignored : this) {
      counter++;
    }
    return counter;
  }

  public static <E> List<E> toList(Iterator<E> iterator) {
    var copy = new ArrayList<E>();
    iterator.forEachRemaining(copy::add);
    return copy;
  }
}
