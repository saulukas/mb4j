package org.mb4j.servlet;

import org.mb4j.controller.url.StaticUrl4Request;
import org.mb4j.controller.url.StaticUrl4RequestResolver;

public class ServletStaticResourceUrlResolver implements StaticUrl4RequestResolver {
  public final String path2home;

  public ServletStaticResourceUrlResolver(String path2home) {
    this.path2home = path2home;
  }

  @Override
  public StaticUrl4Request resolveStaticUrl(String urlFromHome) {
    return new StaticUrl4Request(path2home + urlFromHome);
  }
}
