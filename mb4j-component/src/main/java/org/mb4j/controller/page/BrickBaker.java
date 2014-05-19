package org.mb4j.controller.page;

import org.mb4j.brick.Brick;
import org.mb4j.controller.Request;

public interface BrickBaker {
  Brick bakeBrickFrom(Request request);
}
