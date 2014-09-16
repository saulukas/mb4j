package org.mb4j.liferay;

import org.mb4j.component.ViewUrl;
import org.mb4j.component.ViewUrl4ResponseResolver;
import org.mb4j.component.Request;
import org.mb4j.component.asset.AssetUrl4ResponseResolver;
import org.mb4j.component.ResourceUrlResolver;
import org.mb4j.component.utils.Attributes;
import org.mb4j.component.viewmap.ViewMap;
import org.mb4j.liferay.adapters.PortletFormData4ResponseResolver;

public class PortletViewRequest {

    public static Request of(
            ViewUrl viewUrl,
            String path2home,
            String path2assets,
            Attributes attributes,
            String namespace,
            String authTokenOrNull,
            ResourceUrlResolver resourcesResolver,
            ViewMap viewMap
    ) {
        return new Request(
                viewUrl,
                attributes,
                new AssetUrl4ResponseResolver(path2assets),
                new ViewUrl4ResponseResolver(path2home, viewMap.viewClass2UrlPath()),
                new PortletFormData4ResponseResolver(namespace, authTokenOrNull, viewMap.formClass2Name()),
                resourcesResolver
        );
    }
}
