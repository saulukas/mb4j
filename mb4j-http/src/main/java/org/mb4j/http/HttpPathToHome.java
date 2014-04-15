package org.mb4j.http;

public class HttpPathToHome {
  public static String pathStringToHomeFrom(String pathString) {
    int slashCount = 0;
    for (int i = 0; i < pathString.length(); i++) {
      if (i != 0 && pathString.charAt(i) == '/') {
        slashCount += 1;
      }
    }
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < slashCount; i++) {
      result.append("../");
    }
    return result.toString();
  }
}
