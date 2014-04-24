package org.mb4j.controller.page;

import org.mb4j.controller.ControllerRequest;

public abstract class BrickBakerPage extends Page implements BrickBaker {
  @Override
  public PageResponse handle(ControllerRequest request) {
    return PageResponse.pageResponseWith(bakeBrickFrom(request));
  }
}
