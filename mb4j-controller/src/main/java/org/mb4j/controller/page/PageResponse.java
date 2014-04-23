package org.mb4j.controller.page;

import org.mb4j.brick.Brick;
import org.mb4j.controller.ControllerResponse;

public class PageResponse implements ControllerResponse {
  public final Brick brick;

  private PageResponse(Brick brick) {
    this.brick = brick;
  }

  public static PageResponse pageResponseWith(Brick brick) {
    return new PageResponse(brick);
  }
}
