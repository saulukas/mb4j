package org.mb4j.component.utils;

import com.google.common.base.Optional;

public abstract class Attributes {
  public abstract <T> void setValueOf(AttributeKey<T> key, T value);

  public abstract <T> Optional<T> valueOf(AttributeKey<T> key);
}
