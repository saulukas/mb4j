package org.mb4j.controller;

import java.util.Objects;

public class ControllerRequestAttribute<K, V> {
  public final K key;
  public final V value;

  private ControllerRequestAttribute(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public static <K, V> ControllerRequestAttribute<K, V> requestAttribute(K key, V value) {
    return new ControllerRequestAttribute<>(key, value);
  }

  public static <V> ControllerRequestAttribute<Void, V> requestAttribute(V value) {
    return new ControllerRequestAttribute<>(null, value);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 83 * hash + Objects.hashCode(this.key);
    hash = 83 * hash + Objects.hashCode(this.value);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final ControllerRequestAttribute<?, ?> other = (ControllerRequestAttribute<?, ?>) obj;
    if (!Objects.equals(this.key, other.key)) {
      return false;
    }
    if (!Objects.equals(this.value, other.value)) {
      return false;
    }
    return true;
  }
}
