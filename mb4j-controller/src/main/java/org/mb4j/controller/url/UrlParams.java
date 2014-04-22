package org.mb4j.controller.url;

import static org.mb4j.controller.url.UrlPathString.pathStringOf;

public class UrlParams {
  private static final UrlParams EMPTY = UrlParams.of(UrlPath.empty());
  public final UrlPath path;
  public final NamedParams named;

  private UrlParams(UrlPath path, NamedParams named) {
    this.path = path;
    this.named = named;
  }

  public static UrlParams empty() {
    return EMPTY;
  }

  public static UrlParams of(UrlPath path) {
    return of(path, NamedParams.empty());
  }

  public static UrlParams of(UrlPath path, NamedParams named) {
    return new UrlParams(path, named);
  }

  @Override
  public String toString() {
    return pathStringOf(path) + "?" + named;
  }

  public UrlParams withReplacedParam(String name, String value) {
    return new UrlParams(path, named.withReplacedParam(name, value));
  }

  public UrlParams withDeletedParam(String name) {
    return new UrlParams(path, named.withDeletedParam(name));
  }
}
