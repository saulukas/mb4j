package org.mb4j.servlet;

import org.mb4j.controller.url.StaticResourceUrlResolver;

public class ServletStaticResourceUrlResolver implements StaticResourceUrlResolver {
  public final String path2home;

  public ServletStaticResourceUrlResolver(String path2home) {
    this.path2home = path2home;
  }

  @Override
  public String urlForStaticResource(String urlFromHome) {
    return path2home + urlFromHome;
  }
}
