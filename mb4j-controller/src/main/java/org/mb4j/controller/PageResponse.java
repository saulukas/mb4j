package org.mb4j.controller;

import org.mb4j.brick.Brick;

public class PageResponse extends ViewResponse {
  private PageResponse(Brick brick) {
    super(Type.BRICK, brick, null);
  }

  public static PageResponse pageResponseWith(Brick brick) {
    return new PageResponse(brick);
  }
}
