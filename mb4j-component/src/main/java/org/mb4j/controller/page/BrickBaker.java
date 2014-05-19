package org.mb4j.controller.page;

import org.mb4j.brick.MustacheBrick;
import org.mb4j.controller.Request;

public interface BrickBaker {
  MustacheBrick bakeBrickFrom(Request request);
}
