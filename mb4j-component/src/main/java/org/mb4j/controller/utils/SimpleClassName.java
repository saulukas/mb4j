package org.mb4j.controller.utils;

public class SimpleClassName {
  public static String of(Class<?> klass) {
    String result = "";
    boolean isFirst = true;
    while (klass != null) {
      if (!isFirst) {
        result = "." + result;
      }
      result = klass.getSimpleName() + result;
      klass = klass.getEnclosingClass();
      isFirst = false;
    }
    return result;
  }
}
