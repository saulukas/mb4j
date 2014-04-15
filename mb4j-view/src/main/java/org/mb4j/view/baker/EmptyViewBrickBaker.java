package org.mb4j.view.baker;

import org.mb4j.bricks.EmptyBrick;
import org.mb4j.view.ViewRequest;

public class EmptyViewBrickBaker<P> implements ViewBrickBaker<P> {
  public static final EmptyViewBrickBaker INSTANCE = new EmptyViewBrickBaker();

  @Override
  public EmptyBrick bakeBrick(ViewRequest request, P params) {
    return new EmptyBrick();
  }
}
