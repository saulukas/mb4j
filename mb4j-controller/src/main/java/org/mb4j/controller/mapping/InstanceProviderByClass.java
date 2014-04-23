package org.mb4j.controller.mapping;

public interface InstanceProviderByClass {
  <T> T instanceOf(Class<T> klass);
}
