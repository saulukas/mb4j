package org.mb4j.controller;

import org.mb4j.brick.Brick;

public class PageResponse extends ViewResponse {
  public PageResponse(Brick brick) {
    super(Type.BRICK, brick, null);
  }
}
