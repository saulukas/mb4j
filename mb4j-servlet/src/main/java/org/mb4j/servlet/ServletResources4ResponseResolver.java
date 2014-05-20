package org.mb4j.servlet;

import org.mb4j.component.resource.Resources4ResponseResolver;
import org.mb4j.component.sitemap.MapComponentClass2Name;

public class ServletResources4ResponseResolver extends Resources4ResponseResolver {
  public ServletResources4ResponseResolver(MapComponentClass2Name componentClass2Name) {
    super(componentClass2Name);
  }

  @Override
  protected String resolveResourceUrl(String resourceParamName, String resourceParamValue) {
    return "./?" + resourceParamName + "=" + resourceParamValue;
  }
}
