package org.mb4j.view.baker;

import org.mb4j.Brick;
import org.mb4j.view.ViewRequest;

public interface ViewBrickBaker<P> {
  Brick bakeBrick(ViewRequest request, P params);
}
