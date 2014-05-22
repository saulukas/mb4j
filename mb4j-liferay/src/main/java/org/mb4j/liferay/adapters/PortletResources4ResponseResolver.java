package org.mb4j.liferay.adapters;

import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import javax.portlet.PortletResponse;
import javax.portlet.ResourceURL;
import org.mb4j.component.resource.Resources4ResponseResolver;
import org.mb4j.component.viewmap.MapComponentClass2Name;

public class PortletResources4ResponseResolver extends Resources4ResponseResolver {
  private final LiferayPortletResponse portletResponse;

  public PortletResources4ResponseResolver(
      PortletResponse portletResponse,
      MapComponentClass2Name componentClass2Name
  ) {
    super(componentClass2Name);
    this.portletResponse = (LiferayPortletResponse) portletResponse;
  }

  @Override
  protected String resolveResourceUrl(String resourceParamName, String resourceParamValue) {
    ResourceURL resourceURL = portletResponse.createResourceURL();
    resourceURL.setParameter(resourceParamName, resourceParamValue);
    return resourceURL.toString();
  }
}
