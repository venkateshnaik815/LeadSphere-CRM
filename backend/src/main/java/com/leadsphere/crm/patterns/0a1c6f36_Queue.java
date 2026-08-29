package com.leadsphere.crm.patterns;

import com.iluwatar.commander.exceptions.IsEmptyException;

public class Queue<T> {

  private Node<T> front;
  private Node<T> rear;
  private int size;

  static class Node<V> {
    V value;
    Node<V> next;

    Node(V obj, Node<V> b) {
      value = obj;
      next = b;
    }
  }

  boolean isEmpty() {
    return size == 0;
  }

  void enqueue(T obj) {
    if (front == null) {
      front = new Node<>(obj, null);
      rear = front;
    } else {
      var temp = new Node<>(obj, null);
      rear.next = temp;
      rear = temp;
    }
    size++;
  }

  T dequeue() throws IsEmptyException {
    if (isEmpty()) {
      throw new IsEmptyException();
    } else {
      var temp = front;
      front = front.next;
      size = size - 1;
      return temp.value;
    }
  }

  T peek() throws IsEmptyException {
    if (isEmpty()) {
      throw new IsEmptyException();
    } else {
      return front.value;
    }
  }
}
