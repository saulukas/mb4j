package org.mb4j.controller.resource;

import java.util.ArrayList;
import java.util.Collection;
import org.mb4j.controller.Component;

public abstract class Resources4ResponseResolver {
  public Resources4Response resolveResourcesFor(Component component) {
    Collection<Resource4Response> resources = new ArrayList<>();
    for (Resource resource : component.getResources()) {
      resources.add(new Resource4Response(resolveResourceUrl(component, resource), resource));
    }
    return new Resources4Response(resources);
  }

  protected abstract String resolveResourceUrl(Component component, Resource resource);
}
