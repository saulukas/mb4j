package org.mb4j.liferay;

import org.mb4j.controller.url.StaticUrl4Request;
import org.mb4j.controller.url.StaticUrl4RequestResolver;

public class PortletStaticResourceUrlResolver implements StaticUrl4RequestResolver {
  public final String path2home;

  public PortletStaticResourceUrlResolver(String path2home) {
    this.path2home = path2home;
  }

  @Override
  public StaticUrl4Request resolveStaticUrl(String urlFromHome) {
    if (!urlFromHome.startsWith("/")) {
      urlFromHome = "/" + urlFromHome;
    }
    return new StaticUrl4Request(path2home + urlFromHome);
  }
}
