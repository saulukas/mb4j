package org.mb4j.controller.baker;

import org.mb4j.brick.EmptyBrick;
import org.mb4j.controller.ViewRequest;

public class EmptyViewBrickBaker<P> implements ViewBrickBaker<P> {
  public static final EmptyViewBrickBaker INSTANCE = new EmptyViewBrickBaker();

  @Override
  public EmptyBrick bakeBrick(ViewRequest request, P params) {
    return new EmptyBrick();
  }
}
