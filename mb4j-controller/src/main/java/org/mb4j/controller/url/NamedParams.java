package org.mb4j.controller.url;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.annotation.Nullable;

public class NamedParams {
  private static final NamedParams EMPTY = new NamedParams(Collections.<String, String>emptyMap());
  private final Map<String, String> name2value;

  public NamedParams(Map<String, String> name2value) {
    this.name2value = Collections.unmodifiableMap(createCopyOf(name2value));
  }

  public static NamedParams empty() {
    return EMPTY;
  }

  @Nullable
  public String valueOf(String paramName) {
    return name2value.get(paramName);
  }

  public Set<String> names() {
    return name2value.keySet();
  }

  public boolean isEmpty() {
    return name2value.isEmpty();
  }

  public NamedParams withReplacedParam(String name, String value) {
    Map<String, String> map = createMap();
    map.put(name, value);
    return new NamedParams(map);
  }

  public NamedParams withDeletedParam(String name) {
    Map<String, String> map = createMap();
    map.remove(name);
    return new NamedParams(map);
  }

  private Map<String, String> createMap() {
    return createCopyOf(name2value);
  }

  private static TreeMap<String, String> createCopyOf(Map<String, String> original) {
    return new TreeMap<>(original);
  }

  @Override
  public String toString() {
    return name2value.toString();
  }
}
