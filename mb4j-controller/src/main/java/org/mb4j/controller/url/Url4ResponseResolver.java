package org.mb4j.controller.url;

public class Url4ResponseResolver {
  private final String path2staticAssets;

  public Url4ResponseResolver(String path2staticAssets) {
    this.path2staticAssets = path2staticAssets.isEmpty() || path2staticAssets.endsWith("/")
        ? path2staticAssets
        : path2staticAssets + "/";
  }

  public Url4Response resolveUrl(String staticAssetUrl) {
    if (staticAssetUrl.startsWith("/")) {
      staticAssetUrl = staticAssetUrl.substring(1);
    }
    return new Url4Response(path2staticAssets + staticAssetUrl);
  }
}
