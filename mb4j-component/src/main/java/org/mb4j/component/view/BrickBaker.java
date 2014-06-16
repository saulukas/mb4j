package org.mb4j.component.view;

import org.mb4j.brick.MustacheBrick;

public interface BrickBaker {
  MustacheBrick bakeBrick(ViewRequest request);
}
