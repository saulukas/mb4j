package org.mb4j.controller.url;

public class Url4RequestResolver {
  private final String path2home;

  public Url4RequestResolver(String path2home) {
    this.path2home = path2home.isEmpty() || path2home.endsWith("/")
        ? path2home
        : path2home + "/";
  }

  public Url4Request resolveUrl(String urlFromHome) {
    if (urlFromHome.startsWith("/")) {
      urlFromHome = urlFromHome.substring(1);
    }
    return new Url4Request(path2home + urlFromHome);
  }
}
