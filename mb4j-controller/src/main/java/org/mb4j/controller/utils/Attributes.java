package org.mb4j.controller.utils;

import com.google.common.base.Optional;
import java.util.Map;

public abstract class Attributes {
  public abstract <T> void setValueOf(AttributeKey<T> key, T value);

  public abstract <T> Optional<T> valueOf(AttributeKey<T> key);

  public void putAll(Map<AttributeKey, Object> entries) {
    for (Map.Entry<AttributeKey, Object> entry : entries.entrySet()) {
      setValueOf(entry.getKey(), entry.getValue());
    }
  }
}
