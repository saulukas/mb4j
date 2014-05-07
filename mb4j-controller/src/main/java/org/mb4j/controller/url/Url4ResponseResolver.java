package org.mb4j.controller.url;

public class Url4ResponseResolver {
  private final String path2staticResources;

  public Url4ResponseResolver(String path2staticResources) {
    this.path2staticResources = path2staticResources.isEmpty() || path2staticResources.endsWith("/")
        ? path2staticResources
        : path2staticResources + "/";
  }

  public Url4Response resolveUrl(String staticResourceUrl) {
    if (staticResourceUrl.startsWith("/")) {
      staticResourceUrl = staticResourceUrl.substring(1);
    }
    return new Url4Response(path2staticResources + staticResourceUrl);
  }
}
