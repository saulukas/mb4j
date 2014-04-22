package org.mb4j.controller;

import static org.mb4j.controller.PageResponse.pageResponseWith;

public abstract class BrickBakerPage implements BrickBaker, Page {
  @Override
  public PageResponse handle(ControllerRequest request) {
    return pageResponseWith(bakeBrickFrom(request));
  }
}
