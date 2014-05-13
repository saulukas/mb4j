package org.mb4j.controller.url;

public class AssetUrl4ResponseResolver {
  private final String path2assets;

  public AssetUrl4ResponseResolver(String path2assets) {
    this.path2assets = path2assets.isEmpty() || path2assets.endsWith("/")
        ? path2assets
        : path2assets + "/";
  }

  public AssetUrl4Response resolveUrl(String assetUrl) {
    if (assetUrl.startsWith("/")) {
      assetUrl = assetUrl.substring(1);
    }
    return new AssetUrl4Response(path2assets + assetUrl);
  }
}
