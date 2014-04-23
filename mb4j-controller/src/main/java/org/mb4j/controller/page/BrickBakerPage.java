package org.mb4j.controller.page;

import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.page.BrickBaker;

public abstract class BrickBakerPage implements BrickBaker, Page {
  @Override
  public PageResponse handle(ControllerRequest request) {
    return PageResponse.pageResponseWith(bakeBrickFrom(request));
  }
}
