package com.leadsphere.crm.patterns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LruCache {
  static class Node {
    private final String userId;

    private UserAccount userAccount;

    private Node previous;

    private Node next;

    Node(final String id, final UserAccount account) {
      this.userId = id;
      this.userAccount = account;
    }
  }

  private int capacity;

  private Map<String, Node> cache = new HashMap<>();

  private Node head;

  private Node end;

  public LruCache(final int cap) {
    this.capacity = cap;
  }

  public UserAccount get(final String userId) {
    if (cache.containsKey(userId)) {
      var node = cache.get(userId);
      remove(node);
      setHead(node);
      return node.userAccount;
    }
    return null;
  }

  public void remove(final Node node) {
    if (node.previous != null) {
      node.previous.next = node.next;
    } else {
      head = node.next;
    }
    if (node.next != null) {
      node.next.previous = node.previous;
    } else {
      end = node.previous;
    }
  }

  public void setHead(final Node node) {
    node.next = head;
    node.previous = null;
    if (head != null) {
      head.previous = node;
    }
    head = node;
    if (end == null) {
      end = head;
    }
  }

  public void set(final String userId, final UserAccount userAccount) {
    if (cache.containsKey(userId)) {
      var old = cache.get(userId);
      old.userAccount = userAccount;
      remove(old);
      setHead(old);
    } else {
      var newNode = new Node(userId, userAccount);
      if (cache.size() >= capacity) {
        LOGGER.info("# Cache is FULL! Removing {} from cache...", end.userId);
        cache.remove(end.userId); // remove LRU data from cache.
        remove(end);
        setHead(newNode);
      } else {
        setHead(newNode);
      }
      cache.put(userId, newNode);
    }
  }

  public boolean contains(final String userId) {
    return cache.containsKey(userId);
  }

  public void invalidate(final String userId) {
    var toBeRemoved = cache.remove(userId);
    if (toBeRemoved != null) {
      LOGGER.info("# {} has been updated! " + "Removing older version from cache...", userId);
      remove(toBeRemoved);
    }
  }

  public boolean isFull() {
    return cache.size() >= capacity;
  }

  public UserAccount getLruData() {
    return end.userAccount;
  }

  public void clear() {
    head = null;
    end = null;
    cache.clear();
  }

  public List<UserAccount> getCacheDataInListForm() {
    var listOfCacheData = new ArrayList<UserAccount>();
    var temp = head;
    while (temp != null) {
      listOfCacheData.add(temp.userAccount);
      temp = temp.next;
    }
    return listOfCacheData;
  }

  public void setCapacity(final int newCapacity) {
    if (capacity > newCapacity) {
      // Behavior can be modified to accommodate
      // for decrease in cache size. For now, we'll
      clear();
      // just clear the cache.
    } else {
      this.capacity = newCapacity;
    }
  }
}
