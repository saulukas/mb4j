package org.mb4j.controller.resource;

public class Resource {
  public String name;
  public boolean enabled = true;
  public boolean visible = true;

  public Resource(String name) {
    this.name = name;
  }
}
