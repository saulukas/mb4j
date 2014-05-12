package org.mb4j.controller.resource;

import org.mb4j.controller.Component;

public abstract class Resources4ResponseResolver {
  public Resources4Response resolveResourcesFor(Component component) {
    return new Resources4Response();
  }
}
