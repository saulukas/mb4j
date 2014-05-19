package org.mb4j.controller.resource;

import java.util.ArrayList;
import java.util.Collection;
import org.mb4j.controller.Component;
import org.mb4j.controller.sitemap.MapComponentClass2Name;

public abstract class Resources4ResponseResolver {
  public static final String RESOURCE_PARAM_NAME = "mb(r)";
  private final MapComponentClass2Name componentWithResourcesClass2Name;

  public static class ParamValue {
    public static final String DELIMITER = "-";
    public final String componentName;
    public final String resourceName;

    public ParamValue(String componentName, String resourceName) {
      this.componentName = componentName;
      this.resourceName = resourceName;
    }

    public static ParamValue from(String string) {
      int index = string.indexOf(DELIMITER);
      if (index <= 0) {
        throw new RuntimeException("No delimiter '" + DELIMITER + "' found in [" + string + "].");
      }
      return new ParamValue(string.substring(0, index), string.substring(index + 1));
    }

    @Override
    public String toString() {
      return componentName + DELIMITER + resourceName;
    }
  }

  public Resources4ResponseResolver(MapComponentClass2Name componentWithResourcesClass2Name) {
    this.componentWithResourcesClass2Name = componentWithResourcesClass2Name;
  }

  public Resources4Response resolveResourcesFor(Component component) {
    Collection<Resource4Response> resources = new ArrayList<>();
    for (Resource resource : component.getResources()) {
      String componentName = componentWithResourcesClass2Name.componentNameOf(component.getClass());
      ParamValue value = new ParamValue(componentName, resource.name);
      resources.add(new Resource4Response(
          resolveResourceUrl(RESOURCE_PARAM_NAME, value.toString()),
          resource));
    }
    return new Resources4Response(resources);
  }

  protected abstract String resolveResourceUrl(String resourceParamName, String resourceParamValue);
}
