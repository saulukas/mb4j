package org.mb4j.controller;

public abstract class BrickBakerPage implements BrickBaker, Page {
  @Override
  public PageResponse handle(ControllerRequest request) {
    return PageResponse.pageResponseWith(bakeBrickFrom(request));
  }
}
