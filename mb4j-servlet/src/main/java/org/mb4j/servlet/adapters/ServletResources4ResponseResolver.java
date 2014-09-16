package org.mb4j.servlet.adapters;

import org.mb4j.component.ResourceUrlResolver;
import org.mb4j.component.viewmap.MapComponentClass2Name;

public class ServletResources4ResponseResolver extends ResourceUrlResolver {

    public ServletResources4ResponseResolver(MapComponentClass2Name componentClass2Name) {
        super(componentClass2Name);
    }

    @Override
    protected String resolveResourceUrl(String resourceParamName, String resourceParamValue) {
        return "./?" + resourceParamName + "=" + resourceParamValue;
    }
}
