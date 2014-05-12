package org.mb4j.controller.resource;

import org.mb4j.controller.Component;

public class Resource {
  public final Component component;
  public final String name;
  public boolean enabled = true;
  public boolean visible = true;

  public Resource(Component component, String name) {
    this.component = component;
    this.name = name;
  }
}
