package org.mb4j.controller;

import org.mb4j.controller.path.ViewPath;
import static org.mb4j.controller.path.ViewPathString.pathStringOf;

public class ViewParams {
  private static final ViewParams EMPTY = ViewParams.of(ViewPath.empty());
  public final ViewPath path;
  public final NamedParams named;

  private ViewParams(ViewPath path, NamedParams named) {
    this.path = path;
    this.named = named;
  }

  public static ViewParams empty() {
    return EMPTY;
  }

  public static ViewParams of(ViewPath path) {
    return of(path, NamedParams.empty());
  }

  public static ViewParams of(ViewPath path, NamedParams named) {
    return new ViewParams(path, named);
  }

  @Override
  public String toString() {
    return pathStringOf(path) + "?" + named;
  }

  public ViewParams withReplacedParam(String name, String value) {
    return new ViewParams(path, named.withReplacedParam(name, value));
  }

  public ViewParams withDeletedParam(String name) {
    return new ViewParams(path, named.withDeletedParam(name));
  }
}
