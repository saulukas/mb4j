package org.mb4j.liferay.adapters;

import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import javax.portlet.PortletResponse;
import javax.portlet.ResourceURL;
import org.mb4j.component.ResourceUrlResolver;
import org.mb4j.component.viewmap.ComponentNameResolver;

public class PortletResourceUrlResolver extends ResourceUrlResolver {

    private final LiferayPortletResponse portletResponse;

    public PortletResourceUrlResolver(
            PortletResponse portletResponse,
            ComponentNameResolver componentNames
    ) {
        super(componentNames);
        this.portletResponse = (LiferayPortletResponse) portletResponse;
    }

    @Override
    protected String resolveResourceUrl(String resourceParamName, String resourceParamValue) {
        ResourceURL resourceURL = portletResponse.createResourceURL();
        resourceURL.setParameter(resourceParamName, resourceParamValue);
        return resourceURL.toString();
    }
}
