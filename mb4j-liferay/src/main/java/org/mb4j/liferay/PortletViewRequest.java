package org.mb4j.liferay;

import org.mb4j.component.ViewLocator;
import org.mb4j.component.ViewUrlResolver;
import org.mb4j.component.Request;
import org.mb4j.component.AssetUrlResolver;
import org.mb4j.component.ResourceUrlResolver;
import org.mb4j.component.utils.Attributes;
import org.mb4j.component.viewmap.ViewMap;
import org.mb4j.liferay.adapters.PortletFormData4ResponseResolver;

public class PortletViewRequest {

    public static Request of(
            ViewLocator viewLocator,
            String path2home,
            String path2assets,
            Attributes attributes,
            String namespace,
            String authTokenOrNull,
            ResourceUrlResolver resourcesResolver,
            ViewMap viewMap
    ) {
        return new Request(
                viewLocator,
                attributes,
                new AssetUrlResolver(path2assets),
                new ViewUrlResolver(path2home, viewMap.viewClass2UrlPath()),
                new PortletFormData4ResponseResolver(namespace, authTokenOrNull, viewMap.formClass2Name()),
                resourcesResolver
        );
    }
}
