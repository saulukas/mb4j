package org.mb4j.controller.page;

import org.mb4j.brick.Brick;
import org.mb4j.controller.Response;

public class PageResponse implements Response {
  public final Brick brick;

  private PageResponse(Brick brick) {
    this.brick = brick;
  }

  public static PageResponse pageResponseWith(Brick brick) {
    return new PageResponse(brick);
  }
}
