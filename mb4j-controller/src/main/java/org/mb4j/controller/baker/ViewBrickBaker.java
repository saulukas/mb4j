package org.mb4j.controller.baker;

import org.mb4j.brick.Brick;
import org.mb4j.controller.ViewRequest;

public interface ViewBrickBaker<P> {
  Brick bakeBrick(ViewRequest request, P params);
}
