package org.mb4j.liferay;

import org.mb4j.view.url.StaticResourceUrlResolver;

public class PortletStaticResourceUrlResolver implements StaticResourceUrlResolver {
    public final String path2home;

    public PortletStaticResourceUrlResolver(String path2home) {
        this.path2home = path2home;
    }

    @Override
    public String urlForStaticResource(String urlFromHome) {
        if (!urlFromHome.startsWith("/")) {
            urlFromHome = "/" + urlFromHome;
        }
        return path2home + urlFromHome;
    }
}
