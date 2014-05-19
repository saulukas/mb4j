package org.mb4j.controller.url;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class NamedParams {
  private static final NamedParams EMPTY = new NamedParams(Collections.<String, String>emptyMap());
  private final Map<String, String> name2value;

  public NamedParams(Map<String, String> name2value) {
    this.name2value = Collections.unmodifiableMap(createCopyOf(name2value));
  }

  public static NamedParams empty() {
    return EMPTY;
  }

  public String valueOrNullOf(String paramName) {
    return name2value.get(paramName);
  }

  public Set<String> names() {
    return name2value.keySet();
  }

  public boolean isEmpty() {
    return name2value.isEmpty();
  }

  public NamedParams withReplaced(String name, String value) {
    Map<String, String> map = createMap();
    map.put(name, value);
    return new NamedParams(map);
  }

  public NamedParams withDeleted(String name) {
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

  public Map<String, String> asMap() {
    return name2value;
  }

  @Override
  public String toString() {
    return name2value.toString();
  }
}
