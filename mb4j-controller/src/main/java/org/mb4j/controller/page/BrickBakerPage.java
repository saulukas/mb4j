package org.mb4j.controller.page;

import org.mb4j.controller.Request;

public abstract class BrickBakerPage extends Page implements BrickBaker {
  @Override
  public PageResponse handle(Request request) {
    return PageResponse.pageResponseWith(bakeBrickFrom(request));
  }
}
