package org.mb4j.servlet.adapters;

import org.mb4j.component.ResourceUrlResolver;
import org.mb4j.component.viewmap.ComponentNameResolver;

public class ServletResourceUrlResolver extends ResourceUrlResolver {

    public ServletResourceUrlResolver(ComponentNameResolver componentNames) {
        super(componentNames);
    }

    @Override
    protected String resolveResourceUrl(String resourceParamName, String resourceParamValue) {
        return "./?" + resourceParamName + "=" + resourceParamValue;
    }
}
