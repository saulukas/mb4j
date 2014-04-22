package org.mb4j.controller;

import org.mb4j.brick.Brick;

public interface BrickBaker {
  Brick bakeBrickFrom(ControllerRequest request);
}
