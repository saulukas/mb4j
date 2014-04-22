package org.mb4j.controller.path;

import java.util.LinkedList;
import java.util.List;

public class UrlPathBuilder {
  private final List<String> pathSegments = new LinkedList<>();

  private UrlPathBuilder() {
  }

  public static UrlPathBuilder urlPath() {
    return new UrlPathBuilder();
  }

  public UrlPathBuilder with(String segment) {
    pathSegments.add(segment);
    return this;
  }

  public UrlPath instance() {
    return new UrlPath(pathSegments);
  }
}
