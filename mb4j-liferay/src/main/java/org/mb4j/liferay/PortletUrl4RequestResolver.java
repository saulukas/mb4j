package org.mb4j.liferay;

import org.mb4j.controller.url.Url4Request;
import org.mb4j.controller.url.Url4RequestResolver;

public class PortletUrl4RequestResolver implements Url4RequestResolver {
  public final String path2home;

  public PortletUrl4RequestResolver(String path2home) {
    this.path2home = path2home;
  }

  @Override
  public Url4Request resolveUrl(String urlFromHome) {
    if (!urlFromHome.startsWith("/")) {
      urlFromHome = "/" + urlFromHome;
    }
    return new Url4Request(path2home + urlFromHome);
  }
}
