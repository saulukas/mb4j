package org.mb4j.controller.resource;

import java.util.ArrayList;
import java.util.Collection;
import org.mb4j.controller.Component;
import org.mb4j.controller.sitemap.MapComponentClass2Name;

public abstract class Resources4ResponseResolver {
  private static final String RESOURCE_PARAM_NAME = "mb(r)";
  private final MapComponentClass2Name componentWithResourcesClass2Name;

  public Resources4ResponseResolver(MapComponentClass2Name componentWithResourcesClass2Name) {
    this.componentWithResourcesClass2Name = componentWithResourcesClass2Name;
  }

  public Resources4Response resolveResourcesFor(Component component) {
    Collection<Resource4Response> resources = new ArrayList<>();
    for (Resource resource : component.getResources()) {
      String resourceParamValue = resourceParamValueFor(component, resource);
      resources.add(new Resource4Response(
          resolveResourceUrl(RESOURCE_PARAM_NAME, resourceParamValue),
          resource
      ));
    }
    return new Resources4Response(resources);
  }

  private String resourceParamValueFor(Component component, Resource resource) {
    String componentName = componentWithResourcesClass2Name.componentNameOf(component.getClass());
    return componentName + "#" + resource.name;
  }

  protected abstract String resolveResourceUrl(String resourceParamName, String resourceParamValue);
}
