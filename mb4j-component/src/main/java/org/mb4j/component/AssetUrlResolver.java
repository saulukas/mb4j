package org.mb4j.component;

public class AssetUrlResolver {

    private final String path2assets;

    public AssetUrlResolver(String path2assets) {
        this.path2assets = path2assets.isEmpty() || path2assets.endsWith("/")
                ? path2assets
                : path2assets + "/";
    }

    public AssetUrl resolveUrl(String assetUrl) {
        if (assetUrl.startsWith("/")) {
            assetUrl = assetUrl.substring(1);
        }
        return new AssetUrl(path2assets + assetUrl);
    }
}
