package org.mb4j.view.path;

import java.util.LinkedList;
import java.util.List;

public class ViewPathBuilder {
  private final List<String> pathSegments = new LinkedList<String>();

  private ViewPathBuilder() {
  }

  public static ViewPathBuilder viewPath() {
    return new ViewPathBuilder();
  }

  public ViewPathBuilder with(String segment) {
    pathSegments.add(segment);
    return this;
  }

  public ViewPath instance() {
    return new ViewPath(pathSegments);
  }
}
