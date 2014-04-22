package org.mb4j.controller;

import org.mb4j.brick.Brick;

public class PageResponse implements ControllerResponse {
  public final Brick brick;

  private PageResponse(Brick brick) {
    this.brick = brick;
  }

  public static PageResponse pageResponseWith(Brick brick) {
    return new PageResponse(brick);
  }
}
