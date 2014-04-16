package org.mb4j.brick;

public interface BrickBaker<P> {
  Brick bakeBrick(P params);
}
