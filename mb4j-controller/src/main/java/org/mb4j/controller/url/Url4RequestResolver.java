package org.mb4j.controller.url;

public class Url4RequestResolver {
  private final String path2staticResources;

  public Url4RequestResolver(String path2staticResources) {
    this.path2staticResources = path2staticResources.isEmpty() || path2staticResources.endsWith("/")
        ? path2staticResources
        : path2staticResources + "/";
  }

  public Url4Request resolveUrl(String urlFromStaticResources) {
    if (urlFromStaticResources.startsWith("/")) {
      urlFromStaticResources = urlFromStaticResources.substring(1);
    }
    return new Url4Request(path2staticResources + urlFromStaticResources);
  }
}
